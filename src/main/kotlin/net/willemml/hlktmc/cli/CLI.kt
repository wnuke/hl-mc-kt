package net.willemml.hlktmc.cli

import net.willemml.hlktmc.cli.commands.*
import net.willemml.ktcmd.Call
import net.willemml.ktcmd.CommandManager

class CLI {
    private val commandManager = CommandManager<CLIMessage>().apply {
        loadCommands(
            listOf(
                connectBot,
                launchAll,
                launchMinecraftBot,
                listBots,
                removeBot,
                sendFromMinecraftBot
            ).toTypedArray()
        )
    }

    init {
        println("Type help for a list of commands.")
        while (true) {
            print("hl-mc-kt > ")
            val commandString = readLine()
            commandString?.let { commandManager.runCommand(CLIMessage(commandString, this)) }
        }
    }
}

class CLIMessage(message: String, val cli: CLI) : Call(message) {
    override fun respond(message: String) {
        println(message)
    }

    override fun error(message: String) {
        respond("Error: $message")
    }

    override fun success(message: String) {
        respond("Success: $message")
    }

    override fun info(message: String) {
        respond("Info: $message")
    }
}