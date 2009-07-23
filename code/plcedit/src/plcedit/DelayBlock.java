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
    private long delaytime = 0;

    public DelayBlock(int uid) {
        this.uid = uid;
        this.ctype = CodeType.Delay;
    }

    public DelayBlock() {
        this(UIDGenerator.getNext());
    }
    
    public void setDelayTime(long newtime)
    {
        delaytime = newtime;
    }

    public void setDelayTime(String newtime)
    {
        delaytime = Long.parseLong(newtime);
    }
    
    public long getDelayTime()
    {
        return delaytime;
    }
    
    public String getStringDelayTime()
    {
        return String.valueOf(delaytime);
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
               "delayms(" + String.valueOf(delaytime) + ");";
    }
}
