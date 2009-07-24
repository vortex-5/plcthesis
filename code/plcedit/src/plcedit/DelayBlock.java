/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package plcedit;

/**
 *
 * @author huangkf
 */
public class DelayBlock extends CodeBlock {
    private String delaytime = "0";

    public DelayBlock(int uid) {
        this.uid = uid;
        this.ctype = CodeType.Delay;
    }

    public DelayBlock() {
        this(UIDGenerator.getNext());
    }
    
    public void setDelayTime(String newtime)
    {
        if (newtime == "") newtime = "0";
        delaytime = newtime;
    }
    
    public String getDelayTime()
    {
        return delaytime;
    }

    @Override
    public String getTypeString() {
        return "DELAY";
    }

    @Override
    public String getCode() throws Exception{
                return getUIDStringLabel() + newline +
               "//////////////////////////////////////" + newline +
               "//        DELAY                     //" + newline +
               "//////////////////////////////////////" + newline +
               "delayms(" + delaytime + ");";
    }
}
