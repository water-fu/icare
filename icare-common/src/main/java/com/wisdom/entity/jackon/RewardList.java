package com.wisdom.entity.jackon;

import java.util.List;

/**
 * 奖励人员列表
 * Created by fusj on 16/5/8.
 */
public class RewardList {

    private List<Reward> list;

    public List<Reward> getList() {
        return list;
    }

    public void setList(List<Reward> list) {
        this.list = list;
    }

    public static class Reward {
        private Integer rewardId;

        public Integer getRewardId() {
            return rewardId;
        }

        public void setRewardId(Integer rewardId) {
            this.rewardId = rewardId;
        }
    }
}
