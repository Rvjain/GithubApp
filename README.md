## Github App

### Architecture
- Using the MVVM architecture (Model - View - ViewModel)

### Folders/Files
#### Data
- This folder contains the remote data sources.
- `api` folder defines an interface and uses retrofit to define routes for access the repos/contributors
- `model` Data model used to store data from apis (Se/De-serializations) is done using the moshi
#### DI
- Using `hilt` library for dependency injection
- `NetworkModule` is used to provide the `GithubApi` to repositories
#### Repository
- Provides access to the data from apis.
- `RepoRepository` - Provides the repositories and `ContributorsRepository` provides the contributors for owner and repo
#### UseCase
- `RepoUseCase` provides top `count` number of repos using the `RepoRepository`
- `ContributionUseCase` provides the contributors for particular owner and repo
#### UI
- UI is written using `Jetpack Compose`
- `AppNavigation` uses the jetpack navigation lib with compose to provide app navigation
- `RepoViewModel` loads the repositories and updates the BaseViewState which is collected by the HomeUI to show repos
- `ContributorsViewModel` loads the contributors based on owner/repo provided when repo is clicked. ContributorScreen shows a list of contributors

### Installation
1. Clone the repo and run the app on phone or emulator.
OR
1. Install using the apk provided in the root folder   

