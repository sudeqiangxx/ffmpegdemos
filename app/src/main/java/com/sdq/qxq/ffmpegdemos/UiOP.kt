package com.sdq.qxq.ffmpegdemos

sealed class UiOP {
    object Show:UiOP()
    object Hide:UiOP()
    class TranslateX(val px:Float):UiOP()
    class TranslateY(val px:Float):UiOP()

}
