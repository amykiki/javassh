package org.mybatis.smvc.controller;

import org.mybatis.smvc.service.MessageService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * Created by Amysue on 2016/6/10.
 */
@Controller
@RequestMapping("/msg")
public class MsgController {
    private MessageService msgService;

    @Resource(name = "msgService")
    public void setMsgService(MessageService msgService) {
        this.msgService = msgService;
    }
}
