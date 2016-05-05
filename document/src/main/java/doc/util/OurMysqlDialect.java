package doc.util;

import org.hibernate.dialect.MySQL5InnoDBDialect;

/**
 * Created by Amysue on 2016/5/4.
 */
public class OurMysqlDialect extends MySQL5InnoDBDialect {

    @Override
    public String getTableTypeString() {
        return " ENGINE=InnoDB DEFAULT CHARSET=utf8";
    }
}
