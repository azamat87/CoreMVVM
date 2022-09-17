package com.app.coremvvm.presentation

interface NavigationCommunication {

    interface Update : Communication.Update<NavigationScreen>

    interface Observe : Communication.Observe<NavigationScreen>

    interface Mutable : Update, Observe

    class Base : Communication.SinglePostUpdate<NavigationScreen>(), Mutable
}