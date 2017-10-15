package com.chilicool.hdtools.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by chilicool on 2017/9/9.
 */
@Controller
@RequestMapping(value="/query")
public class QueryController {

    @RequestMapping(value = "/specifyQuery.html")
    public String specifyQuery(){
        return "query/specify_query";
    }
}
