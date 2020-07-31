package com.qifan.javase.Socket;

import java.io.File;
import java.io.Serializable;

public class FileCommand implements Serializable {

    private String commandType;
    private String commandArgument;
    private File file = null;
    private String path;

    public FileCommand(String destFile) {
        this.path = destFile;
        File temp = new File(path);
        if (temp.exists()) {
            file = temp;
        }
    }

    public FileCommand(String commandType, String commandArgument) {
        this.commandType = commandType;
        this.commandArgument = commandArgument;
    }

    public String getCommandType() {
        return commandType;
    }

    public void setCommandType(String commandType) {
        this.commandType = commandType;
    }

    public String getCommandArgument() {
        return commandArgument;
    }

    public void setCommandArgument(String commandArgument) {
        this.commandArgument = commandArgument;
    }

    public File getFile() {
        return file;
    }

    public String getPath() {
        return path;
    }
}
