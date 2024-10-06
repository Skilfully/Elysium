package org.xyit.elysium.var;

import static org.xyit.elysium.ElysiumLoginRegister.YamlValueString;
import static org.xyit.elysium.var.PublicVar.*;

public class SendMessage {

    public static void getMessage() {
        String file = YamlValueString("plugins\\ElysiumLogin","setting.yml","language");
        String path = "plugins\\ElysiumLogin\\Local_Languages";
        prefix = YamlValueString(path,file,"prefix");
        Invalid_Parameters = YamlValueString(path,file,"WrongParameters");
        onlyPlayer = YamlValueString(path,file,"onlyPlayer");
        ServerError = YamlValueString(path,file,"register.ServerError");
        successfully = YamlValueString(path,file,"register.Successfully");
        already = YamlValueString(path,file,"register.Already");
        noSame = YamlValueString(path,file,"register.NoSame");
    }
}
