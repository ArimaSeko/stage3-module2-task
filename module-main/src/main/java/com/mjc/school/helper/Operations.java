package com.mjc.school.helper;
import static com.mjc.school.helper.Constant.OPERATION;
    public enum Operations {
        GET_ALL_NEWS(1, "Get all news.properties."),
        GET_NEWS_BY_ID(2, "Get news.properties by id."),
        CREATE_NEWS(3, "Create news.properties."),
        UPDATE_NEWS(4, "Update news.properties."),
        REMOVE_NEWS_BY_ID(5, "Remove news.properties by id."),
        EXIT(0, "Exit.");

        private final Integer operationNumber;
        private final String operation;

        Operations(Integer operationNumber, String operation) {
            this.operationNumber = operationNumber;
            this.operation = operation;
        }

        public String getOperation() {
            return OPERATION + operation;
        }

        public String getOperationWithNumber() {
            return operationNumber + " - " + operation;
        }
    }