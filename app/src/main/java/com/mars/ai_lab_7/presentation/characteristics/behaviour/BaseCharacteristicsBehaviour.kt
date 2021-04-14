package com.mars.ai_lab_7.presentation.characteristics.behaviour

abstract class BaseCharacteristicsBehaviour : CharacteristicsBehaviour {
    protected var behaviourTarget: CharacteristicsBehaviourTarget? = null

    override fun setTarget(target: CharacteristicsBehaviourTarget) {
        behaviourTarget = target
    }
}