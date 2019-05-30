# orange-server

## 接口文档：

### 用户

#### 1、登录

```
path: /orange/login
method: post
body:
{
    code: ''
}
res: 
{
    data: {
        token: '',
    },
    err_msg: '',
    code: 0,
}
```

#### 2、获取用户信息

```
path: /orange/user
method: get
res: 
{
    data: {
        int userId;
        String username;
        String openid;
        String profile;
        String state;
        Timestamp dataCreate_LastTime;
        Timestamp dataChange_LastTime;
    },
    err_msg: '',
    code: 0,
}
```

#### 3、更新用户

```
path: /orange/user
method: put
body:
{
    String username;
    String profile;
}
res: 
{
    err_msg: '',
    code: 0,
}
```



### 活动

#### 1、创建活动 

```
path: /orange/activity
method: post
body:
{
    title: '',
    description: '', // 描述
    startTime: '',  timestamp
    endTime: '',	timestamp
    unit: '', // 单位
    location: '', // 地点
    quantity: 	//数量 int
}
res: 
{
    data: {
        activityId: '',
    },
    err_msg: '',
    code: 0,
}
```

#### 2、修改活动

```
path: /orange/activity
method: put
body:
{
    title: '',
    description: '', // 描述
    startTime: '',  timestamp
    endTime: '',	timestamp
    unit: '', // 单位
    location: '', // 地点
    quantity: 	//数量 int
}
res: 
{
    err_msg: '',
    code: 0,
}
```

#### 3、删除活动

```
path: /orange/activity/{activityId}
method: delete
res:
{
    code: 0,
    err_msg: '',
}
```

#### 4、通过活动ID获取活动信息

```
path: /orange/activity/{activityId}
method: get
res:
{
    data: {
        int activityId;
        int userId;
        String title;
        String description;
        Timestamp startTime;
        Timestamp endTime;
        String unit;
        String location;
        int quantity;
        int state;
        Timestamp dataCreate_LastTime;
        Timestamp dataChange_LastTime;
        List<Part> parts;
        WeChatUser userInfo;
    },
    code: 0,
    err_msg: '',
}

```

P.S:

```
Part{
    int partId;
    int userId;
    int activityId;
    int quantity;
    int shareType;
    int shareId;
    int state;
    Timestamp dataCreate_LastTime;
    Timestamp dataChange_LastTime;
}
WeChatUser{
    int userId;
    String username;
    String openid;
    String profile;
    String state;
    Timestamp dataCreate_LastTime;
    Timestamp dataChange_LastTime;
}
```

#### 5、通过用户获取活动信息

```
path: /orange/activity
method: get
res:
{
    data: [{
        int activityId;
        int userId;
        String title;
        String description;
        Timestamp startTime;
        Timestamp endTime;
        String unit;
        String location;
        int quantity;
        int state;
        Timestamp dataCreate_LastTime;
        Timestamp dataChange_LastTime;
        List<Part> parts;
        WeChatUser userInfo;
    }],
    code: 0,
    err_msg: '',
}
```
#### 6、结束活动
```
path: /orange/activity/close/{activityId}
method: post
res:
{
    code: 0,
    err_msg: '',
}
```

### 报名

#### 1、新增排队

```
path: /orange/queue
method: post
body:
{
    activityId: '',	活动id
    partQuantity: '',	报名数量
    shareId: '',	分享者id
    shareType: ''	分享者类型
}
res: 
{
    data: {
        partId: '',
    },
    err_msg: '',
    code: 0,
}
```

#### 2、删除排队

```
path: /orange/queue/{partId}
method: delete
body:
{
    partId: '',	排队ID
}
res: 
{
    err_msg: '',
    code: 0,
}
```

#### 3、修改排队

```
path: /orange/queue
method: put
body:
{
    activityId: '',	活动id
    partQuantity: ''	报名数量
}
res: 
{
    err_msg: '',
    code: 0,
}
```

#### 4、查询一个排队的所有报名信息

```
path: /orange/queue/activity/{activityId}
method: get
res:
{
    data: [
    {
        int partId;
        int userId;
        int activityId;
        int quantity;
        int shareType;
        int shareId;
        int state;
        Timestamp dataCreate_LastTime;
        Timestamp dataChange_LastTime;
        WeChatUser userInfo;
     }],
    code: 0,
    err_msg: '',
}
```

#### 5、根据ActivityID和userID查询

```
path: /orange/queue/activity_with_user/{activityId}
method: get
res:
{
    data: [{
        int partId;
        int userId;
        int activityId;
        int quantity;
        int shareType;
        int shareId;
        int state;
        Timestamp dataCreate_LastTime;
        Timestamp dataChange_LastTime;
        WeChatUser userInfo; 
     }],
    code: 0,
    err_msg: '',
}
```

#### 6、根据userID查询

```
path: /orange/queue
method: get
res:
{
    data: [{
        int partId;
        int userId;
        int activityId;
        int quantity;
        int shareType;
        int shareId;
        int state;
        Timestamp dataCreate_LastTime;
        Timestamp dataChange_LastTime;
        WeChatUser userInfo; 
     }],
    code: 0,
    err_msg: '',
}
```
