//非常に大きな整数を扱えるよう、任意精度整数の型(BigInt)を導入
import scala.math.BigInt

//オブジェクトの宣言
object Factorial extends App {

  //階乗を求めるメソッド（メソッド内で自身を再帰的に呼び出す形で実装）
  //if式...JavaScriptなどのif文とは異なり、返すのは値だけ
  //等価を表す演算子は "==" ("==="ではない)
  def factorial(i: BigInt): BigInt = if(i == 0) 1 else i * factorial(i-1)
  println(factorial(1000))
}