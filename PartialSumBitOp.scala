/* PartialSumDFS.scalaと同様の部分和の問題を、ビット演算を用いて解いてみる */
/* ビット演算による実装は高いパフォーマンスを発揮できるが、可読性は下がる。 */

/* 
  問題：
  整数n個で作られた数列aがあります。(= n個の整数を要素として持つSeq)
  数列aの中の整数からいくつかを選び、その和が整数kとなる組み合わせが存在するかどうかを示してください。
  また、存在する場合には、その組み合わせも表示してください。
  なお、n,kはともに整数で、-10億<=n<=10億 -10億<=k<=10億 とします。
*/

/* 
  ビット演算で解く場合の指針（コピペして手直し）

  この部分和の問題で与えられた数列aの最大の長さは20
  このビット列の右から20ビット分を使うとパターンが表現できる。

  数列の長さが20の場合には、0から2の20乗から1を引いた数(1048575)までをビットのパターンとして認識して、
  数列の和を確かめていく。

  1048575を2進数で表現すると、00000000 00001111 11111111 11111111 となる。

  つまり 0から1048575までカウンターを上げながら、カウンターのInt値の下位20ビットを読み取っていく。
  そして 整数のシーケンスの添字に対して、
  •ビットが1であるならば、与えられた数列の対応する添字の整数を足す 
  •ビットが0であれば足さない
  として、最後に目的の合計値とマッチするかを確認する。

  要するに、数列a内の20個の要素を、20ビットの各桁に対応させ、足すor足さないを、1or0で表現する。
*/

object PartialSumBitOp extends App {
  val a = Seq(1, 10, 49, 3, 8, 13, 7, 23, 60, -500, 42, 599, 45, -23, 1, 10, 49, 3, 8, 13)  //数列a
  val n = a.length  //数列aの長さ
  val k = 444  //欲しい部分和

  var isMatch = false  //計算結果とkが一致したかどうかのフラグ
  var bitsCounter = 0  //カウンターとなるビット列(20ビット)を表す変数を用意(ビットカウンター)

  /* 
    -1を補数表現で表すと 11111111 11111111 11111111 11111111
    それを、数列aの長さ(n)分だけ、<<で左シフトする。

    例えば、今回のnは20なので、n数だけ左シフトすると 11111111 11110000 00000000 00000000 となる。
    それを補数演算子~で反転させると、00000000 00001111 11111111 11111111 になり、
    これが今回のビット列のカウンターの最大値として機能することになる。
  */
  val max = ~(-1 << n)  //ビット列のカウンターの最大値 00000000 00001111 11111111 11111111

  /* 
    計算結果がkと一致していない状態で、
    bitsCounterの値(初期値は00000000 00000000 00000000 00000000)が、
    ビット列のカウンターの最大値(00000000 00001111 11111111 11111111)になるまでwhileループ

    whileループ中に、部分和とkが一致した場合は、ループを抜ける
  */
  while(!isMatch && bitsCounter <= max){

    var sum = 0  //部分和の計算結果(初期値は0)

    /* 
      部分和を計算していくループ

      ここでのiは、00000000 00000000 00000000 00000001 の最下位ビットの 1 を、
      論理積で取得したいbitsCounterの桁に合わせるまでに必要な左シフト数を表すので、
      bitsCounterの最下位ビットを取得したい時は、左シフト数は0
      bitsCounterの下から2ビット目を取得したい時は、左シフト数は1
      :
      :
      bitsCounterの下から19ビット目を取得したい時は、左シフト数は18
      bitsCounterの下から20ビット目(ここが最上位)を取得したい時は、左シフト数は19(n - 1)
      となる
    */
    for(i <- 0 to (n - 1)){

      /* 
        ビット論理積をすることで、対象ビット列(今回なら bitsCounter)の特定の桁の状態を取得できる。
        これを「マスクする」という。
      */

      /*
        まず、bitsCounterの特定の桁の状態が知りたいので、
        その桁と同じ桁だけが1となっているビット列を作成する
        ↓
        次に、作成したビット列とbitsCounterの論理積を取り、bitsCounterの特定の桁の状態を取得する
        ↓
        その桁が1なら、その桁に対応した(=その桁数をインデックスとする)数列aの要素を加える
      */
      val mask = 1 << i  //00000000 00000000 00000000 00000001 をi分だけ左シフト
      val masked = bitsCounter & mask  //論理積でマスクしてbitsCounterの特定の桁の状態を取得
      if(masked != 0) sum = sum + a(i)  //部分和に加算
    }

    //部分和の計算結果が一致していれば、isMatchをtrueにし、一致していなければビットカウンターを1増やす
    if(sum == k){
      isMatch = true
    } else {
      bitsCounter = bitsCounter + 1
    }
  }

  //isMatchの中身で処理を切り分ける
  if(isMatch){
    /* whileループ終了までに、部分和とkが一致した場合の処理 */

    //部分和を構成する整数群を格納するために、空のSeqを用意
    var result: Seq[Int] = Seq()  

    /* 
      部分和を求めるときと同様の手法。

      マスクすることでbitsCounterの特定の桁の状態を取得し、
      その桁が1なら、その桁数をインデックスとした数列aの要素を、resultに追加していく。
    */
    for(i <- 0 to (n - 1)){
      val mask = 1 << i
      val masked = bitsCounter & mask
      if(masked != 0) result = result :+ a(i)
    }

    //部分和を構成する整数群をListで表示(List型はSeqの一種)
    println(s"Yes ${result}")
  } else {
    /* whileループ終了までに、部分和とkが一致しなかった場合の処理 */
    println("No")
  }
}