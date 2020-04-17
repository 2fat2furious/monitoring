package sample.data;


import java.util.Objects;

public class ChildGroup {
    private long gartenId;
    private String groupName;
    private long yearId;
    private long childId;
    private String childFullName;

    public ChildGroup(long gartenId, String groupName, long yearId, long childId, String childFullName) {
        this.gartenId = gartenId;
        this.groupName = groupName;
        this.yearId = yearId;
        this.childId = childId;
        this.childFullName = childFullName;
    }

    public GroupId getId() {
        return new GroupId(gartenId, groupName, yearId);
    }

    public long getGartenId() {
        return gartenId;
    }

    public void setGartenId(long gartenId) {
        this.gartenId = gartenId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public long getYearId() {
        return yearId;
    }

    public void setYearId(long yearId) {
        this.yearId = yearId;
    }

    public long getChildId() {
        return childId;
    }

    public void setChildId(long childId) {
        this.childId = childId;
    }

    public String getChildFullName() {
        return childFullName;
    }

    public void setChildFullName(String childFullName) {
        this.childFullName = childFullName;
    }

    public static class GroupId {
        private long gartenId;
        private String groupName;
        private long yearId;

        public GroupId(long gartenId, String groupName, long yearId) {
            this.gartenId = gartenId;
            this.groupName = groupName;
            this.yearId = yearId;
        }

        public long getGartenId() {
            return gartenId;
        }

        public void setGartenId(long gartenId) {
            this.gartenId = gartenId;
        }

        public String getGroupName() {
            return groupName;
        }

        public void setGroupName(String groupName) {
            this.groupName = groupName;
        }

        public long getYearId() {
            return yearId;
        }

        public void setYearId(long yearId) {
            this.yearId = yearId;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            GroupId groupId = (GroupId) o;
            return gartenId == groupId.gartenId &&
                    yearId == groupId.yearId &&
                    groupName.equals(groupId.groupName);
        }

        @Override
        public int hashCode() {
            return Objects.hash(gartenId, groupName, yearId);
        }
    }
}
