package com.huahuo.model.mail.dtos;

import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.huahuo.model.common.dtos.PageRequestDto;
import lombok.Data;

/**
 * @作者 花火
 * @创建日期 2022/11/15 11:05
 */
@Data
public class EsSearchDto extends PageRequestDto {
    String searchWords;

}
