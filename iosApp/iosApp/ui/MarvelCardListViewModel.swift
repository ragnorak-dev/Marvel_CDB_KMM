//
//  MarvelCardListViewModel.swift
//  iosApp
//
//  Created by Raul Illan on 5/6/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared
import Combine

class MarvelCardListViewModel: ObservableObject {
    @Published var marvelList: [MarvelCardModel] = []

    @Published public private(set) var loaded = false

    private var cancellable: AnyCancellable?
    
    let marvelCardListUseCase: GetMarvelCardListUseCase

    init(charactersListUseCase: GetMarvelCardListUseCase = GetMarvelCardListUseCaseHelper.getMarvelCardListUseCase(GetMarvelCardListUseCaseHelper())()) {
        self.marvelCardListUseCase = charactersListUseCase
        }

    func getMarvelCardList() {
       cancellable = Future<[MarvelCardModel], Error> { promise in
           self.marvelCardListUseCase.execute { resultDataKt, error in
               resultDataKt?.onSuccess(action: { marverCardList in
                   promise(.success(marverCardList as? [MarvelCardModel] ?? []))
               })
               
               resultDataKt?.onFailure(action: { nserror in
                   promise(.failure(nserror as! Error))
               })
           }
           }.eraseToAnyPublisher()
           .receive(on: DispatchQueue.main)
           .sink { result in
                           
               self.loaded = true
               
               switch result {
               case .failure (let error) :
                   print(error.localizedDescription)
               default:
                   break
               }
           } receiveValue: { (data: [MarvelCardModel]) in
                   self.marvelList = data
           }
    }
}
