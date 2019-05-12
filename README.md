# orange-server

接口文档：

1.创建活动
```
path: /orange/activity
method: post
body:
{
    title: '',
    description: '', // 描述
    startTime: '',
    endTime: '',
    attribute: '', // 属性
    postion: '', // 地点
}

res: 
{
    data: {
        id: '',
    },
    err_msg: '',
    code: 0,
}
```

2.修改活动

```
path: /orange/activity
method: put
其余同创建
```

3.删除活动

```
path: /orange/activity?id=<ID>
method: delete
res:
{
    code: 0,
    err_msg: '',
}
```

4.活动信息
```
path: /orange/activity?id=<ID>
method: get
res:
{
    id: '',
    title: '',
    description: '', // 描述
    startTime: '',
    endTime: '',
    attribute: '', // 属性
    postion: '', // 地点
    queue: [], // 报名人数
}
```

5.报名
```
path: /orange/queue
method: post
body:
{
    userId: '',
    qunId: '',
    type: '',
}
```

6.用户这块在想想
