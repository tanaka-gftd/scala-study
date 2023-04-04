//正規表現で文字列検索

object RegexSearch extends App {
  val text = "カワカドカドカドドワンゴカドカドンゴドワドワンゴドワカワカドンゴドワ"
  val pattern = "ドワンゴ"

  /* 
    r...String型のメソッドで、文字列を正規表現のRegex型に変換する
    findAllIn(~~)...Regex型のメソッドで、与えられた正規表現にマッチするIteratorを取得(戻り値はMatchIteratorという型)
    matchData...マッチした文字列に対する様々な情報を与える Iterator[Match] 型を与える
    matchData.map(_.start)...すべてのマッチ情報から出現箇所のインデックスを取得  map関数によりIterator[Match] 型が Iterator[Int] 型 に変換される
    toList...IteratorをListに変換
  */
  val matchIndexes = pattern.r.findAllIn(text).matchData.map(_.start).toList
  println(s"出現場所: ${matchIndexes}")
}