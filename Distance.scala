object Distance extends App {

  //val...Scalaにおける定数宣言（Javascriptで言うところのconst）
  //（再代入可能の変数を宣言したい場合はvarを使う）
  val kilometersPerHours = 11.0
  val minutes = 100.0
  val distance = kilometersPerHours * minutes / 60.0

  /* 
    Scalaにおける数値の型
    値型     範囲                  記述例
    Byte    1バイトの符号付き整数    1.toByte
    Short   2バイトの符号付き整数    1.toShort
    Int     4バイトの符号付き整数    1
    Long    8バイトの符号付き整数    1L

    Float   4バイトの浮動小数点数    1.0F
    Double  8バイトの浮動小数点数    1.0
   */

  //文字列のリテラルの前にsを記述することで、${変数}で変数を文字列の中で読み出すことができる（Javascriptで言うところのテンプレートリテラル）
  //リテラル...プログラムのソースコード上に直接記述される値の事
  println(s"走った距離は${distance}キロメートルです")
}