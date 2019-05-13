package com.sdq.qxq.ffmpegdemos

import java.io.Serializable

/**
 * @description:
 * @author: lenna
 * @date: 2019-05-13
 * @update: 2019-05-13
 * @version: 1.0
 */
class Ui(val uiOps:List<UiOP> = emptyList()){
    operator fun plus(uiOp: UiOP)=Ui(uiOps+uiOp)

}