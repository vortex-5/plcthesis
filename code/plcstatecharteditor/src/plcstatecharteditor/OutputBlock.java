/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package plcstatecharteditor;

/**
 *
 * @author huangkf
 */
public class OutputBlock extends CodeBlock{
    public enum PORTS {PortA,PortB,PortC,PortD} //ports we can be mapped to
    
    private PORTS port;

    public OutputBlock() {
        this.uid = UIDGenerator.getNext();
        this.ctype = CodeType.Output;


        //TODO: determine if we want a default port at all
        this.port = PORTS.PortA;
    }

    public PORTS getPort() {
        return port;
    }

    public void setPort (PORTS port) {
        this.port = port;
    }

    @Override
    public String getTypeString() {
        return "OUTPUT";
    }

    @Override
    public String getCode() {
        throw new UnsupportedOperationException("Not supported yet.");
    }


}
