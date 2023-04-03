//N予備校Scalaレッスン2-3中級問題
//分からなかったので、解説を参照しました（素因数分解の処理を含めて、一から実装しようとしてしまった）


/* 
  以下のオブジェクトを呼び出すことで、別ファイル(Factorization.scala)で定義した素因数分解を実行する関数が呼び出せる

  Seqのmap関数は、Seqの要素全てに与えられた関数を適用した新しいSeqを取得できる
  この関数は、JavaScriptのArrayのmap関数とほとんど同じ動きをし、println関数も引数として渡せる
*/
object PrintFactorization extends App {
  //Seq内の各要素に素因数分解を実施して、その解で新しいSeqを作成する(この場合は4つのSeqが作成される)
  //作成された4つの各Seqにprintln関数を使い、Seqの中身をコンソールに表示していく
  Seq(32, 25, 90, 510510).map(Factorization.factorization).map(println)
}
