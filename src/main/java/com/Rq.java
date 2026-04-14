package com;

public class Rq {
    private String cmd;
    private String actionName;
    private String param;

    public Rq(String cmd) {
        this.cmd = cmd.trim();

        String[] cmdBits = this.cmd.split(" ", 2);

        actionName = cmdBits[0];

        if (cmdBits.length == 2) {
            param = cmdBits[1];
        } else {
            param = "";
        }
    }

    public String getCmd() {
        return cmd;
    }

    public String getActionName() {
        return actionName;
    }

    public String getParam() {
        return param;
    }
}
