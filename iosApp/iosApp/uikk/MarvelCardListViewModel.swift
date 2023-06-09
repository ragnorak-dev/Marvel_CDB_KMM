import Foundation
import shared
import Combine

class MarvelCardListViewModel: ObservableObject {
    @Published var marvelList: [CharacterModel] = []

    @Published public private(set) var loaded = false

    private var cancellable: AnyCancellable?


    let marvelCardListUseCase: GetMarvelCardListUseCase

    init(charactersListUseCase: GetMarvelCardListUseCase = GetMarvelCardListUseCaseHelper.getMarvelCardList(GetMarvelCardListUseCaseHelper())()) {
        self.marvelCardListUseCase = charactersListUseCase
        }

    private func getMarvelCardList() -> AnyPublisher<[CharacterModel], Error> {
        return Deferred {
            Future { promise in
                self.characterListUseCase.getCharactersList() { response, error in

                    if response is ResultDataSuccess {
                        promise(.success((response as? ResultDataSuccess)?.data as? [CharacterModel] ?? []))
                    }
                    if response is ResultDataFailure {
                        promise(.failure(NSError(domain: "", code: 401, userInfo: [ NSLocalizedDescriptionKey: (response as? ResultDataFailure)?.message])))
                    }

                    if let error = error {
                        promise(.failure(error))
                    }
                }
            }
        }.eraseToAnyPublisher()
    }

    func loadCharacters() {
        cancellable = getCharactersList()
            .receive(on: DispatchQueue.main)
            .sink { result in

                self.loaded = true

                switch result {
                case .failure (let error) :
                    print(error.localizedDescription)
                default:
                    break
                }
            } receiveValue: { (data: [CharacterModel]) in
                    self.marvelList = data
            }
        }
}
