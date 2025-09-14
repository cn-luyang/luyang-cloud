package io.github.luyang.platform.open.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.github.luyang.platform.open.beans.entity.TokenEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * Token 相关 Mapper
 *
 * @author yang.lu
 */
@Mapper
public interface TokenMapper extends BaseMapper<TokenEntity> {
}
