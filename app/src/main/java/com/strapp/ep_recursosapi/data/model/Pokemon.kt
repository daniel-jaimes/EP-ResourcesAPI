package com.strapp.ep_recursosapi.data.model

class Pokemon {
    var name: String
        get() = name
    var heigh : Int
        get() = heigh

    constructor(name: String, heigh: Int){
        this.name = name
        this.heigh = heigh
    }

}