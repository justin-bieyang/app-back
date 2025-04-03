package com.justin.app_back.controller;

import com.github.pagehelper.PageInfo;
import com.justin.app_back.pojo.BackendUser;
import com.justin.app_back.service.BackendUserService;
import com.justin.app_back.utils.JwtUtil;
import com.justin.app_back.vo.ResultVo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 后台使用者接口（包括admin和user）
 * @author 小杜
 * @version 1.0
 * @since 1.0
 */

@RestController
@RequestMapping("/user")
public class BackendUserController {

    @Resource
    private BackendUserService backendUserService;

    /**
     * 分页查询
     * @param backendUser
     * @param pageNum
     * @return
     */
    @PostMapping("/page")
    public ResultVo page(@RequestBody BackendUser backendUser,@RequestParam(defaultValue = "1") int pageNum) {
        PageInfo pageInfo = backendUserService.getPage(backendUser,pageNum);
        return ResultVo.success("用户分页查询成功",pageInfo);
    }

    /**
     * 删除用户
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public ResultVo delete(@PathVariable("id") Integer id) {
        backendUserService.deleteById(id);
        return ResultVo.success("删除用户成功",null);
    }

    /**
     * 更新用户信息
     * @param backendUser
     * @param request
     * @return
     */
    @PostMapping("/updateType")
    public ResultVo updateType(@RequestBody BackendUser backendUser, HttpServletRequest request) {
        String token = request.getHeader("token");
        String username = JwtUtil.getUsername(token);
        backendUserService.updateUserType(backendUser, username);
        return ResultVo.success("更新用户成功",null);
    }

}
