public class arglist {
    String argname;
    String value;

    arglist(String argname) {
        this.argname = argname;
        this.value = "";
    }

    arglist(arglist a) {
        this.argname = a.argname;
        this.value = a.value;
    }
}