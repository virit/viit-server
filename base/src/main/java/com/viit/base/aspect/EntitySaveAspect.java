package com.viit.base.aspect;

import com.viit.base.lang.entity.Entity;
import com.viit.base.entity.SysUser;
import com.viit.base.utils.ContextUtils;
import com.viit.utils.lang.DateUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * 实体保存切入
 *
 * @author chentao
 * @version 2019-10-28
 */
// @Aspect
// @Component
public class EntitySaveAspect {

    private void entityInsertSetter(Entity entity) {
        // 对实体设置默认值
        Date currentDate = DateUtils.currentDate();
        entity.setCreateDate(currentDate);
        entity.setUpdateDate(currentDate);
        SysUser currentUser = ContextUtils.currentUser();
        // 设置创建人
        entity.setCreateUserId(currentUser.getId());
        // 设置更新人
        entity.setUpdateUserId(currentUser.getId());
    }

    /**
     * 切入点
     */
    // @Before("execution(* com.viit..*.*Service.saveBatch(..))")
    public void doBeforeSaveBatch(JoinPoint point) {
        Object[] args = point.getArgs();
        // 判断参数存在
        if (args.length > 0) {
            List<Entity> entityList = (List<Entity>) args[0];
            entityList.forEach(this::entityInsertSetter);
        }
    }

    // @Before("execution(* com.viit..*.*Mapper.insert(..))")
    public void doBeforeInsert(JoinPoint point) {
        Object[] args = point.getArgs();
        // 判断参数存在
        if (args.length > 0) {
            Entity entity = (Entity)args[0];
            this.entityInsertSetter(entity);
        }
    }

    // @Before("execution(* com.viit..*.*Mapper.update(..))")
    public void doBeforeUpdate(JoinPoint point) {
        Object[] args = point.getArgs();
        // 判断参数存在
        if (args.length > 0) {
            Entity entity = (Entity)args[0];
            // 对实体设置默认值
            entity.setUpdateDate(DateUtils.currentDate());
            SysUser currentUser = ContextUtils.currentUser();
            // 设置更新人
            entity.setUpdateUserId(currentUser.getId());
        }
    }
}
