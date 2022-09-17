package com.app.coremvvm.presentation


interface ProgressCommunication {

    interface Update : Communication.Update<Visibility>

    interface Observe : Communication.Observe<Visibility>

    interface Mutable : Update, Observe

    class Base : Communication.SinglePostUpdate<Visibility>(), Mutable

}