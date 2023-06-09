//
//  MarvelCardListView.swift
//  iosApp
//
//  Created by Raul Illan on 6/6/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared

struct MarvelCardListView: View {
    
    @StateObject public var viewModel = MarvelCardListViewModel()
    
    var body: some View {
        ScrollView {
            VStack() {
                if viewModel.loaded {
                    
                    if !viewModel.marvelList.isEmpty {
                        marvelList
                    } else {
                        Text("marvel card vacío")
                    }
                } else {
                    Text("cargando...")
                }
            }.onAppear {
                viewModel.getMarvelCardList()
            }
        }.frame(maxWidth: .infinity)
    }
    
    @ViewBuilder
    var marvelList: some View {
        
        ForEach(viewModel.marvelList, id: \.self) { hero in
            Text(hero.name)
        }
    }
}
