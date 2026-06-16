package org.firstinspires.ftc.teamcode.utils

import com.seattlesolvers.solverslib.command.Command
import com.seattlesolvers.solverslib.command.button.GamepadButton
import com.seattlesolvers.solverslib.gamepad.GamepadEx
import com.seattlesolvers.solverslib.gamepad.GamepadKeys

// Gamepad buttons
fun GamepadEx.a()               : GamepadButton     { return this.getGamepadButton(GamepadKeys.Button.A)           ;    }
fun GamepadEx.b()               : GamepadButton     { return this.getGamepadButton(GamepadKeys.Button.B)           ;    }
fun GamepadEx.x()               : GamepadButton     { return this.getGamepadButton(GamepadKeys.Button.X)           ;    }
fun GamepadEx.y()               : GamepadButton     { return this.getGamepadButton(GamepadKeys.Button.Y)           ;    }
fun GamepadEx.start()           : GamepadButton     { return this.getGamepadButton(GamepadKeys.Button.START)       ;    }
fun GamepadEx.back()            : GamepadButton     { return this.getGamepadButton(GamepadKeys.Button.BACK)        ;    }
fun GamepadEx.dpadDown()        : GamepadButton     { return this.getGamepadButton(GamepadKeys.Button.DPAD_DOWN)   ;    }
fun GamepadEx.dpadUp()          : GamepadButton     { return this.getGamepadButton(GamepadKeys.Button.DPAD_UP)     ;    }
fun GamepadEx.dpadRight()       : GamepadButton     { return this.getGamepadButton(GamepadKeys.Button.DPAD_RIGHT)  ;    }
fun GamepadEx.dpadLeft()        : GamepadButton     { return this.getGamepadButton(GamepadKeys.Button.DPAD_LEFT)   ;    }
fun GamepadEx.rightBumper()     : GamepadButton     { return this.getGamepadButton(GamepadKeys.Button.RIGHT_BUMPER);    }
fun GamepadEx.leftBumper()      : GamepadButton     { return this.getGamepadButton(GamepadKeys.Button.LEFT_BUMPER) ;    }

// Gamepad buttons commands'
fun GamepadButton.onTrue    (command: Command): GamepadButton  { this.whenPressed              (command); return this   }
fun GamepadButton.whileTrue (command: Command): GamepadButton  { this.whileActiveContinuous    (command); return this   }
fun GamepadButton.onFalse   (command: Command): GamepadButton  { this.whenInactive             (command); return this   }
