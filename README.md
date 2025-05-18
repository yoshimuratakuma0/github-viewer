# github-viewer

GitHub ユーザーを閲覧できるアプリ

# 環境構築

API Limit の上限を上げたい場合は `secret.properties` をプロジェクトのルートに追加して、`GITHUB_TOKEN`
を追加してください

# 使用技術

- Hilt
- OkHttp
- Retrofit
- Kotlin Serialization
- Coroutines
- Jetpack Compose
- Roborazzi
- Composable Preview Scanner

# 各モジュールの依存関係

## 概要

```mermaid
graph TD
    app --> design-system
    app --> features
    app --> data
    app --> domain
    data --> domain
    features --> design-system
    features --> domain
```

## 詳細

```mermaid
graph TD

subgraph domain
  Entities[Entities]
  IRepositories[Repositories]
  UseCases[UseCases]
end

subgraph data
  Repositories[Repositories]
  Datasources[Datasources]
  DataModels[Data models]
end

subgraph features
  ViewModels[ViewModels]
  Screens[Screens]
end

subgraph design-system
  Components[Components]
  DesignTokens[Design Tokens]
end

UseCases --> Entities
IRepositories --> Entities

Repositories --> Datasources
Repositories --> DataModels
Datasources --> DataModels

Screens --> ViewModels

Components --> DesignTokens

Repositories --> IRepositories
Repositories --> Entities

ViewModels --> UseCases
ViewModels --> Entities

Screens --> Components
Screens --> DesignTokens

```
