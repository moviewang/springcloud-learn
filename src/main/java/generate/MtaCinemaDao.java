package generate;

import generate.MtaCinema;
import java.math.BigDecimal;

public interface MtaCinemaDao {
    int deleteByPrimaryKey(BigDecimal cinemaid);

    int insert(MtaCinema record);

    int insertSelective(MtaCinema record);

    MtaCinema selectByPrimaryKey(BigDecimal cinemaid);

    int updateByPrimaryKeySelective(MtaCinema record);

    int updateByPrimaryKey(MtaCinema record);
}