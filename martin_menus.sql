INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time)
VALUES (2747, '马丁策略', 2632, 2, 'martin-group', '', 1, 0, 'M', '0', '0', '', 'documentation', 'admin', NOW(), '', NULL);

INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time)
VALUES (2748, '马丁策略信息', 2747, 1, 'martinStrategyInfo', 'strategy/martinStrategyInfo/index', 1, 0, 'C', '0', '0', 'strategy:martinStrategyInfo:list', '#', 'admin', NOW(), '', NULL);
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time)
VALUES (2749, '策略信息查询', 2748, 1, '', '', 1, 0, 'F', '0', '0', 'strategy:martinStrategyInfo:query', '#', 'admin', NOW(), '', NULL);
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time)
VALUES (2750, '策略信息新增', 2748, 2, '', '', 1, 0, 'F', '0', '0', 'strategy:martinStrategyInfo:add', '#', 'admin', NOW(), '', NULL);
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time)
VALUES (2751, '策略信息修改', 2748, 3, '', '', 1, 0, 'F', '0', '0', 'strategy:martinStrategyInfo:edit', '#', 'admin', NOW(), '', NULL);
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time)
VALUES (2752, '策略信息删除', 2748, 4, '', '', 1, 0, 'F', '0', '0', 'strategy:martinStrategyInfo:remove', '#', 'admin', NOW(), '', NULL);
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time)
VALUES (2753, '策略信息导出', 2748, 5, '', '', 1, 0, 'F', '0', '0', 'strategy:martinStrategyInfo:export', '#', 'admin', NOW(), '', NULL);

INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time)
VALUES (2754, '马丁策略设置', 2747, 2, 'martinStrategySettings', 'strategy/martinStrategySettings/index', 1, 0, 'C', '0', '0', 'strategy:martinStrategySettings:list', '#', 'admin', NOW(), '', NULL);
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time)
VALUES (2755, '策略设置查询', 2754, 1, '', '', 1, 0, 'F', '0', '0', 'strategy:martinStrategySettings:query', '#', 'admin', NOW(), '', NULL);
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time)
VALUES (2756, '策略设置新增', 2754, 2, '', '', 1, 0, 'F', '0', '0', 'strategy:martinStrategySettings:add', '#', 'admin', NOW(), '', NULL);
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time)
VALUES (2757, '策略设置修改', 2754, 3, '', '', 1, 0, 'F', '0', '0', 'strategy:martinStrategySettings:edit', '#', 'admin', NOW(), '', NULL);
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time)
VALUES (2758, '策略设置删除', 2754, 4, '', '', 1, 0, 'F', '0', '0', 'strategy:martinStrategySettings:remove', '#', 'admin', NOW(), '', NULL);
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time)
VALUES (2759, '策略设置导出', 2754, 5, '', '', 1, 0, 'F', '0', '0', 'strategy:martinStrategySettings:export', '#', 'admin', NOW(), '', NULL);

INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time)
VALUES (2760, 'API对应策略', 2747, 3, 'martinStrategyApi', 'strategy/martinStrategyApi/index', 1, 0, 'C', '0', '0', 'strategy:martinStrategyApi:list', '#', 'admin', NOW(), '', NULL);
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time)
VALUES (2761, '策略API查询', 2760, 1, '', '', 1, 0, 'F', '0', '0', 'strategy:martinStrategyApi:query', '#', 'admin', NOW(), '', NULL);
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time)
VALUES (2762, '策略API新增', 2760, 2, '', '', 1, 0, 'F', '0', '0', 'strategy:martinStrategyApi:add', '#', 'admin', NOW(), '', NULL);
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time)
VALUES (2763, '策略API修改', 2760, 3, '', '', 1, 0, 'F', '0', '0', 'strategy:martinStrategyApi:edit', '#', 'admin', NOW(), '', NULL);
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time)
VALUES (2764, '策略API删除', 2760, 4, '', '', 1, 0, 'F', '0', '0', 'strategy:martinStrategyApi:remove', '#', 'admin', NOW(), '', NULL);
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time)
VALUES (2765, '策略API导出', 2760, 5, '', '', 1, 0, 'F', '0', '0', 'strategy:martinStrategyApi:export', '#', 'admin', NOW(), '', NULL);

INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time)
VALUES (2766, '策略订单', 2747, 4, 'martinStrategyOrder', 'strategy/martinStrategyOrder/index', 1, 0, 'C', '0', '0', 'strategy:martinStrategyOrder:list', '#', 'admin', NOW(), '', NULL);
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time)
VALUES (2767, '策略订单查询', 2766, 1, '', '', 1, 0, 'F', '0', '0', 'strategy:martinStrategyOrder:query', '#', 'admin', NOW(), '', NULL);
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time)
VALUES (2768, '策略订单新增', 2766, 2, '', '', 1, 0, 'F', '0', '0', 'strategy:martinStrategyOrder:add', '#', 'admin', NOW(), '', NULL);
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time)
VALUES (2769, '策略订单修改', 2766, 3, '', '', 1, 0, 'F', '0', '0', 'strategy:martinStrategyOrder:edit', '#', 'admin', NOW(), '', NULL);
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time)
VALUES (2770, '策略订单删除', 2766, 4, '', '', 1, 0, 'F', '0', '0', 'strategy:martinStrategyOrder:remove', '#', 'admin', NOW(), '', NULL);
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time)
VALUES (2771, '策略订单导出', 2766, 5, '', '', 1, 0, 'F', '0', '0', 'strategy:martinStrategyOrder:export', '#', 'admin', NOW(), '', NULL);

INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time)
VALUES (2772, '策略分享', 2747, 5, 'martinStrageyShare', 'strategy/martinStrageyShare/index', 1, 0, 'C', '0', '0', 'strategy:martinStrageyShare:list', '#', 'admin', NOW(), '', NULL);
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time)
VALUES (2773, '策略分享查询', 2772, 1, '', '', 1, 0, 'F', '0', '0', 'strategy:martinStrageyShare:query', '#', 'admin', NOW(), '', NULL);
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time)
VALUES (2774, '策略分享新增', 2772, 2, '', '', 1, 0, 'F', '0', '0', 'strategy:martinStrageyShare:add', '#', 'admin', NOW(), '', NULL);
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time)
VALUES (2775, '策略分享修改', 2772, 3, '', '', 1, 0, 'F', '0', '0', 'strategy:martinStrageyShare:edit', '#', 'admin', NOW(), '', NULL);
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time)
VALUES (2776, '策略分享删除', 2772, 4, '', '', 1, 0, 'F', '0', '0', 'strategy:martinStrageyShare:remove', '#', 'admin', NOW(), '', NULL);
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time)
VALUES (2777, '策略分享导出', 2772, 5, '', '', 1, 0, 'F', '0', '0', 'strategy:martinStrageyShare:export', '#', 'admin', NOW(), '', NULL);
