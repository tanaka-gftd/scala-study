//文字列検索アルゴリズム
//先頭から１文字ずつずらしながら、パターンと一致するかを検証していく

object SimpleSearch extends App {
  val text = "カワカドカドカドドワンゴカドカドンゴドワドワンゴドワカワカドンゴドワ".toSeq  //検索元の文字列をSeqに変換
  val pattern = "ドワンゴ".toSeq  //探したい文字列パターンをSeqに変換

  //search関数の結果(=検索結果)を定数に格納
  val result = search(text, pattern)

  //文字列パターンを検索する関数を定義
  def search(text: Seq[Char], pattern: Seq[Char]): Seq[Int] = {  //引数と戻り値を設定

    //関数の中で取得していくmatchIndexes変数(初期値は空のSeq)
    //(*一致場所が複数ある可能性があるので、Seqを使用)
    //本処理では一文字ずつずらしながら検索していく、つまり、変数への再代入を繰り返すので、宣言はvarで行う
    var matchIndexes = Seq[Int]()

    //for式, untilで指定した場合は最後の自身の数は含めない
    //このforの条件式をJavaScript風に書くと、for(let i = 0; i < text.length - 1; i++) になる
    //iは、検索元文字列のインデックス(何番目の文字かを示す、文字列のインデックスは0から始まるので注意)
    for(i <- 0 until text.length - 1){

      //検索元となる文字列から、検証したい文字数だけsliceで切り出す
      val partText = text.slice(i, i + pattern.length)

      //切り出した文字列と、探したい文字列が一致するかどうかをチェック
      //一致していたら、その文字のインデックスを、Seqに要素として加える
      if(isMatch(partText, pattern)) matchIndexes = matchIndexes :+ i
    }
    matchIndexes  //処理結果(中身はSeq)を返す
  }


  //切り出した文字列と、探したい文字列が一致するかどうかをチェックする関数(返り値は真偽値)
  def isMatch(textPart: Seq[Char], pattern: Seq[Char]): Boolean = {

    //isMatch変数のデフォルトはtrueにしておく
    var isMatchFlag = true  //理解しやすいよう、変数名変更

    //レッスン2-4中級問題を解くために、コメントアウト
    /*
    //切り出した文字列と、探したい文字列が一致するかどうかを、1文字ずつループ処理でチェック
    //"文字列の長さが違う" or "文字が一致しない" isMatch変数にfalseを代入
    for(i <- 0 to pattern.length - 1) {
      if(textPart.length < pattern.length || textPart(i) != pattern(i)) isMatch = false
    }
    */

    //思いつかなかったので、解説を参照しました
    //ループを途中で止めるために、whileの条件式として一致したかどうかのフラグを格納した変数(真偽値)を含めるのがポイント
    var i = 0
    while(isMatchFlag && i <= pattern.length - 1){  //isMatchFlagを条件に加えることで、falseになった時点でループを終了できる
      //「iの中身が"textPart.length-1"よりも大きい」「textPart(i) とpattern(i)が一致しない」、どちらか一方でも成立すれば、isMatchFlagにfalse
      //if ( i > textPart.length - 1 || textPart(i) != pattern(i)) isMatchFlag = false //解説だとこの条件だけど、下の方が理解しやすいので書き換え
      if ( textPart.length < pattern.length || textPart(i) != pattern(i)) isMatchFlag = false  
      i = i + 1
    }
    isMatchFlag  //処理結果(中身は真偽値)を返す
  }
  println(s"出現場所: ${result}")
}
