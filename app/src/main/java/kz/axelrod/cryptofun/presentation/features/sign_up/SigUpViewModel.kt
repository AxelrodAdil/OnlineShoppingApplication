package kz.axelrod.cryptofun.presentation.features.sign_up

import kz.axelrod.cryptofun.presentation.mvi.BaseViewModel

class SigUpViewModel :
    BaseViewModel<SigUpContract.State, SigUpContract.Event, SigUpContract.Effect>() {

    override fun createInitialState(): SigUpContract.State {
        return SigUpContract.State
    }

    override fun handleEvent(event: SigUpContract.Event) {

    }
}
