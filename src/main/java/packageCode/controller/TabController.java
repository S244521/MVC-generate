package packageCode.controller;

import packageCode.entity.TabEntity;
import packageCode.service.TabService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;

@Api(tags = "Tab")
@RestController
@RequestMapping("/tab")
public class TabController {

    @Autowired
    private TabService tabService;

    //增加

    @ApiOperation(value = "Tab新增")
    @PostMapping("/insert")
    public boolean add(@RequestBody TabEntity tabentity) {
        return tabService.save(tabentity);
    }

    //通过id查询

    @ApiOperation(value = "Tab通过id查询")
    @GetMapping("/select/{id}")
    public TabEntity getById(@PathVariable("id") Integer id) {
        return tabService.getById(id);
    }

    //查询所有
    @ApiOperation(value = "Tab查询所有")
    @GetMapping("/selectAll")
    public List<TabEntity> getAll() {
        return tabService.list();
    }

    //修改
    @ApiOperation(value = "Tab修改")
    @PostMapping("/update")
    public boolean update(@RequestBody TabEntity tabentity) {
        return tabService.updateById(tabentity);
    }

    //删除
    @ApiOperation(value = "Tab通过id删除")
    @DeleteMapping("/delete/{id}")
    public boolean deleteById(@PathVariable("id") Integer id) {
        return tabService.removeById(id);
    }

    //删除全部表示数据
    @ApiOperation(value = "Tab删除全部")
    @DeleteMapping("/deleteAll")
    public boolean deleteAll() {
        return tabService.remove(null);
    }
}