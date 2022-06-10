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

このアプリはCore, Domain, Data, Presentaion, Appの五つのモジュールに分けられていて、Appは残りの四つの全てのモジュールに依存していて、Domain, Data,
Presentationの三つのモジュールはCoreモジュールに依存しています。 また、Domain, Data, Presentationの三つのモジュールの依存関係は Data ->
Domain <- Presentation となっています。

![modules](https://user-images.githubusercontent.com/88303689/166624713-7adeb862-7b73-4948-b4dd-c03424d1b380.png)

## 詳細

![directories](https://user-images.githubusercontent.com/88303689/166625871-7b482844-44d2-4f29-bf77-37be6e265a0a.png)

※coreモジュールやEntityは全てのモジュールから依存されているため矢印を省略しています。また、appモジュールは全てのモジュールに依存しているため省略しています。

