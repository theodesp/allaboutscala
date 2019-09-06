object poly extends  App {
  import shapeless.poly._

  // choose is a function from Seqs to Options with no type specific cases
  object choose extends (Seq ~> Option) {
    def apply[T](s: Seq[T]) = s.headOption
  }

  choose(Seq(1, 2, 3)) // Some(1)
  choose(Seq('a', 'b', 'c')) // Some('a')

  def pairApply(f: Seq ~> Option) = (f(Seq(1, 2, 3)), f(Seq('a', 'b', 'c')))

  pairApply(choose) // (Some(1), Some('a')
  (List(Seq(1, 3, 5), Seq(2, 4, 6)) map choose)

}