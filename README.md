# github-viewer
Githubのユーザー一覧を取得したり、ユーザーの詳細を表示するアプリ

# 使用技術
- Dagger Hilt
- OkHttp
- Retrofit2
- Kotlin Serialization
- Coroutines
- Flow
- StateFlow
- Jetpack Compose
- Paging3
- MockK

# モジュールの関連図
## 概要
このアプリはCore, Domain, Data, Presentaion, Appの五つのモジュールに分けられていて、Appは残りの四つの全てのモジュールに依存していて、Domain, Data, Presentationの三つのモジュールはCoreモジュールに依存しています。
また、Domain, Data, Presentationの三つのモジュールの依存関係は
Data -> Domain <- Presentation
となっています。

![modules](https://user-images.githubusercontent.com/88303689/166624713-7adeb862-7b73-4948-b4dd-c03424d1b380.png)

## 詳細
![directories](https://user-images.githubusercontent.com/88303689/166625871-7b482844-44d2-4f29-bf77-37be6e265a0a.png)

※coreモジュールやEntityは全てのモジュールから依存されているため矢印を省略しています。また、appモジュールは全てのモジュールに依存しているため省略しています。


# このアプリの改善したいポイント
- Domain層がPaging関連のライブラリに依存してしまっているのが気になります（RepositoryやUseCaseの戻り値の型に使われているだけなのでどっぷり依存しているわけではないのですが）
- ユーザー一覧の画面とユーザー詳細の画面でエラーハンドリングの方法が若干異なってしまったので統一したいです（Pagingのライブラリに癖があってなかなか上手くいかず）
- Jetpack ComposeにおけるViewModelのライフサイクルを正しく設定できていない気がしています（Activityのスコープになっている？Screenのスコープに設定する方法などベストプラクティスがないか調べてみます）
- 上記に関連していそうですが、ユーザーの詳細画面を開くときに画面がチラつくときがあります（Jetpack ComposeのNavigationの動きが怪しい気がするのですが、使い方を間違えてるだけかもしれないので勉強します）

# 新規に足したい機能
- 下スクロール時にAppBarが隠れて、上スクロール時に現れるUX（xmlならCoordinatorLayoutで実装できますが、Jetpack Composeではalpha版のライブラリに依存する必要があったので断念しました）
- WebViewを付けてユーザー詳細画面からURLで飛べる機能
- ユーザー詳細で「フォロー」の項目をタップしたときに、フォロー一覧のリストを表示する機能



