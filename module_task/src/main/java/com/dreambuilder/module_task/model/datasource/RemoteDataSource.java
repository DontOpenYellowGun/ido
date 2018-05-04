package com.dreambuilder.module_main.model.datasource;

/**
 * 文件描述：
 * <p>
 * 作者：   Created by sven on 2017/9/26.
 */

public enum RemoteDataSource {

    INSTANCE;

//    /**
//     * @Method: findGroup <br>
//     * @Description: 查找好友<br>
//     * @Creator: sven <br>
//     * @Date: 2017/12/4 下午2:07
//     */
//    public Observable<BaseEntity<List<User>>> findUser(String username) {
//        Map<String, Object> map = new HashMap<>();
//        map.put("keyword", username);
//        return Service.INSTANCE.getApi()
//                .findUser(MapSort.getSortMap(map))
//                .map(new Function<BaseEntity<List<User>>, BaseEntity<List<User>>>() {
//                    @Override
//                    public BaseEntity<List<User>> apply(BaseEntity<List<User>> listBaseEntity) throws Exception {
//                        AddLocalUserInfo(listBaseEntity);
//                        return listBaseEntity;
//                    }
//                })
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .unsubscribeOn(Schedulers.io());
//    }
}
