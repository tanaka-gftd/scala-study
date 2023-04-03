//平方根を求める関数を導入
import scala.math.sqrt

/* 素因数分解を行う処理 */

object Factorization extends App {

/* 
  ターミナルから「sbt compile」でコンパイル →「sbt console」で REPL起動 →「Factorization.factorization(num)」とすると、
  numが素因数分解され、その解がMapで表示される(*numは整数)

  本ファイルを「sbt run」や「sbt」→「runMain Factorization」などで実行すると、
  println(factorization(510510)) が実行され、510510の素因数分解が行われる
*/

  
  def factorization(target: Int): Map[Int, Int] = {

    //元の数の平方根よりも大きい素因数は、最大で一つしかないので、ループで回す際の最大値として利用する
    val maxDivisor = sqrt(target).toInt  //sqrtの戻り値はDouble型なので、Int型に変換する（小数点以下も切り捨てられる）

    //素因数分解をする関数
    /* 
      num...割られる数（現在の数）
      divisor...割る数
      acc...ここまでの結果を保持する、Map[Int, Int]の連想配列
      戻り値...Map[Int, Int]の連想配列が戻り値となるよう設定

      アルゴリズム
      1.「割る数」が「割る数の最大値」よりも大きい場合、現在の連想配列に現在の「対象」を加えたものを答え(連想配列)とする
      2.「対象」が「割る数」で割り切れる場合、「結果」に「割る数」で割り切れた回数を 1 足して再度関数を実行する
      3.「対象」が割り切れない場合、「割る数」を 1 足して再度関数を実行する
    */
    def factorizationRec(num: Int, divisor: Int, acc: Map[Int, Int]): Map[Int, Int] = {
        if(divisor > maxDivisor) {
          //「割る数」が「割る数の最大値」よりも大きい場合、現在の結果に現在の「対象」を加えたものを答え(連想配列)とする
          //このブロックのnumが最後の素因数となる(numが1なら素因数にならないので、連想配列には手を加えずそのまま返す)
          if(num == 1) acc else acc + (num -> 1) 
        } else if (num % divisor == 0) {  
          //「対象」が「割る数」で割り切れる場合、「結果」に「割る数」で割り切れた回数を 1 足して再度関数を実行する
          //getOrElseメソッド...第1引数に取得したい値を持つkey、第2引数に第1引数で指定したkeyがなかった場合の値を設定
          //割る数をkeyとし、valueの値に 1 追加(keyがなければ、valueを0として、新規にエントリーを作成して 1 追加)
          val nextAcc = acc + (divisor -> (acc.getOrElse(divisor, 0) + 1))  
          factorizationRec(num / divisor, divisor, nextAcc) //(今割った数でもう一回割れるかどうかを確認する) 
        } else {
          //「対象」が割り切れない場合、「割る数」に1を加えて再度関数を実行する
          factorizationRec(num, divisor + 1, acc)
        }
    }

    factorizationRec(target, 2, Map())
  }

  //本ファイルを「sbt run」や「sbt」→「runMain Factorization」などで実行した場合は、以下の行が自動で実行される
  println(factorization(510510))
}
