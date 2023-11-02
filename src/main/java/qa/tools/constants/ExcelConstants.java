/*
 * Copyright (c) 2023. Carlos.GarciaSilva@solera.com
 * All rights reserved to QapterClaims FR team
 */

package qa.tools.constants;

import org.apache.poi.ss.usermodel.IndexedColors;

public class ExcelConstants {
    protected static final String[] STATUS = new String[]{
            "-",
            "ERROR_NODE",
            "ERROR_SELENIUM",
            "ERROR_PERFORMANCE",
            "ERROR_NEED_INVESTIGATE",
            "PR_DEVELOPING",
            "PR_CREATED",
            "PR_MERGED",
            "BUG_CANDIDATE",
            "BUG_REPORTED",
            "BUG_OPEN",
            "BUG_IN_PROGRESS",
            "BUG_IN_TESTING",
            "BUG_RESOLVED",
            "WAITING_SUPPORT",
            "WAITING_NEXT_EXECUTION",
    };
    protected static final IndexedColors[] COLORS = {
            IndexedColors.LIGHT_GREEN,
            IndexedColors.LIGHT_TURQUOISE,
            IndexedColors.LIGHT_YELLOW,
            IndexedColors.ROSE,
            IndexedColors.LIGHT_ORANGE,
            IndexedColors.LIGHT_CORNFLOWER_BLUE
    };

    public static String[] getStatus() {
        return STATUS;
    }

    public static IndexedColors[] getColors() {
        return COLORS;
    }

    public enum ExcelFields {
        ASSIGNED_TO,
        TITLE,
        CASE_ID,
        TEST_STATUS,
        SECTION,
        DESCRIPTION,
        SOLUTION,
        SOLUTION_LINK,
        STATUS
    }

}
