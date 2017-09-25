import java.util.ArrayList;
import java.util.List;

public class Processor {

    public List<Processor> children;
    private Integer value;
    private String stringVal;

    public Processor(Integer value) {
        this.value = value;
        this.stringVal = value.toString();
        children = new ArrayList<Processor>();
    }
	
	//recursively call the function till we reach a leaf, from there, for each parent get the max value of their children 
    public Integer findMax() {

        if (children.isEmpty()) {
            return value;
        }
        for (Processor child : children) {
                int v = child.findMax();
                if (v > this.value) {
                    this.value = v;
                }
        }
        return this.value;
    }
    
	//appending the value starting from the leaf till the root node
    public String findConcate() {

        if (children.isEmpty()) {
            return stringVal;
        }
        for (Processor child : children) {
                String v = child.findConcate();
                this.stringVal=stringVal.concat(v);
        }
        return this.stringVal;
    }

    @Override
    public String toString() {
        return "Processor value=" + value;
    }

}