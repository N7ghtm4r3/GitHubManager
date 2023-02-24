package com.tecknobit.githubmanager.migrations.sourceimports.records;

import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import com.tecknobit.githubmanager.records.parents.InnerClassItem;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.tecknobit.apimanager.trading.TradingTools.roundValue;

/**
 * The {@code Import} class is useful to format a GitHub's organization import
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at:
 * <ul>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/migrations/source-imports#get-an-import-status">
 *             Get an import status</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/migrations/source-imports#start-an-import">
 *             Start an import</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/migrations/source-imports#update-an-import">
 *             Update an import</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/migrations/source-imports#update-git-lfs-preference">
 *             Update Git LFS preference</a>
 *     </li>
 * </ul>
 * @see GitHubResponse
 **/
public class Import extends GitHubResponse {

    /**
     * {@code VCS} list of available VCSs
     **/
    public enum VCS {

        /**
         * {@code subversion} VCS
         **/
        subversion,

        /**
         * {@code git} VCS
         **/
        git,

        /**
         * {@code mercurial} VCS
         **/
        mercurial,

        /**
         * {@code tfvc} VCS
         **/
        tfvc

    }

    /**
     * {@code ImportStatus} list of available import statuses
     **/
    public enum ImportStatus {

        /**
         * {@code auth} import status
         **/
        auth,

        /**
         * {@code error} import status
         **/
        error,

        /**
         * {@code none} import status
         **/
        none,

        /**
         * {@code detecting} import status
         **/
        detecting,

        /**
         * {@code choose} import status
         **/
        choose,

        /**
         * {@code auth_failed} import status
         **/
        auth_failed,

        /**
         * {@code importing} import status
         **/
        importing,

        /**
         * {@code mapping} import status
         **/
        mapping,

        /**
         * {@code waiting_to_push} import status
         **/
        waiting_to_push,

        /**
         * {@code pushing} import status
         **/
        pushing,

        /**
         * {@code complete} import status
         **/
        complete,

        /**
         * {@code setup} import status
         **/
        setup,

        /**
         * {@code unknown} import status
         **/
        unknown,

        /**
         * {@code detection_found_multiple} import status
         **/
        detection_found_multiple,

        /**
         * {@code detection_found_nothing} import status
         **/
        detection_found_nothing,

        /**
         * {@code detection_needs_auth} import status
         **/
        detection_needs_auth

    }

    /**
     * {@code LFS} list of available LFSs
     **/
    public enum LFS {

        /**
         * {@code opt_in} LFS
         **/
        opt_in,

        /**
         * {@code opt_out} LFS
         **/
        opt_out

    }

    /**
     * {@code vcs} of the import
     **/
    private final VCS vcs;

    /**
     * {@code useLfs} whether the import uses lfs
     **/
    private final boolean useLfs;

    /**
     * {@code vcsUrl} the URL of the originating repository
     **/
    private final String vcsUrl;

    /**
     * {@code svcRoot} svc root of the import
     **/
    private final String svcRoot;

    /**
     * {@code tfvcProject} tfvc project of the import
     **/
    private final String tfvcProject;

    /**
     * {@code status} of the import
     **/
    private final ImportStatus status;

    /**
     * {@code statusText} status text of the import
     **/
    private final String statusText;

    /**
     * {@code failedStep} failed step of the import
     **/
    private final String failedStep;

    /**
     * {@code errorMessage} error message of the import
     **/
    private final String errorMessage;

    /**
     * {@code importPercent} import percent of the import
     **/
    private final double importPercent;

    /**
     * {@code commitCount} commit count of the import
     **/
    private final int commitCount;

    /**
     * {@code pushPercent} push percent of the import
     **/
    private final double pushPercent;

    /**
     * {@code hasLargeFiles} whether the import has large files
     **/
    private final boolean hasLargeFiles;

    /**
     * {@code largeFilesSize} large files size of the import
     **/
    private final double largeFilesSize;

    /**
     * {@code largeFilesCount} large files count of the import
     **/
    private final int largeFilesCount;

    /**
     * {@code projectChooses} list of project chooses of the import
     **/
    private final ArrayList<ProjectChoose> projectChooses;

    /**
     * {@code authorsCount} authors count of the import
     **/
    private final int authorsCount;

    /**
     * {@code url} of the import
     **/
    private final String url;

    /**
     * {@code htmlUrl} html url of the import
     **/
    private final String htmlUrl;

    /**
     * {@code authorsUrl} authors url of the import
     **/
    private final String authorsUrl;

    /**
     * {@code repositoryUrl} repository url of the import
     **/
    private final String repositoryUrl;

    /**
     * {@code svnRoot} svn root of the import
     **/
    private final String svnRoot;

    /**
     * Constructor to init a {@link Import}
     *
     * @param vcs:             vcs of the import
     * @param useLfs:          use lfs of the import
     * @param vcsUrl:          vcs url of the import
     * @param svcRoot:         svc root of the import
     * @param tfvcProject:     tfvc project of the import
     * @param status:          status of the import
     * @param statusText:      status text of the import
     * @param failedStep:      failed step of the import
     * @param errorMessage:    error message of the import
     * @param importPercent:   import percent of the import
     * @param commitCount:     commit count of the import
     * @param pushPercent:     push percent of the import
     * @param hasLargeFiles:   whether the import has large files
     * @param largeFilesSize:  large files size of the import
     * @param largeFilesCount: large files count of the import
     * @param projectChooses:  list of project chooses of the import
     * @param authorsCount:    authors count of the import
     * @param url:             url of the import
     * @param htmlUrl:         html url of the import
     * @param authorsUrl:      authors url of the import
     * @param repositoryUrl:   repository url of the import
     * @param svnRoot:         svn root of the import
     **/
    public Import(VCS vcs, boolean useLfs, String vcsUrl, String svcRoot, String tfvcProject, ImportStatus status,
                  String statusText, String failedStep, String errorMessage, double importPercent, int commitCount,
                  double pushPercent, boolean hasLargeFiles, double largeFilesSize, int largeFilesCount,
                  ArrayList<ProjectChoose> projectChooses, int authorsCount, String url, String htmlUrl, String authorsUrl,
                  String repositoryUrl, String svnRoot) {
        super(null);
        this.vcs = vcs;
        this.useLfs = useLfs;
        this.vcsUrl = vcsUrl;
        this.svcRoot = svcRoot;
        this.tfvcProject = tfvcProject;
        this.status = status;
        this.statusText = statusText;
        this.failedStep = failedStep;
        this.errorMessage = errorMessage;
        this.importPercent = importPercent;
        this.commitCount = commitCount;
        this.pushPercent = pushPercent;
        this.hasLargeFiles = hasLargeFiles;
        this.largeFilesSize = largeFilesSize;
        this.largeFilesCount = largeFilesCount;
        this.projectChooses = projectChooses;
        this.authorsCount = authorsCount;
        this.url = url;
        this.htmlUrl = htmlUrl;
        this.authorsUrl = authorsUrl;
        this.repositoryUrl = repositoryUrl;
        this.svnRoot = svnRoot;
    }

    /**
     * Constructor to init a {@link Import}
     *
     * @param jImport: import details as {@link JSONObject}
     **/
    public Import(JSONObject jImport) {
        super(jImport);
        vcs = VCS.valueOf(hResponse.getString("vcs"));
        useLfs = hResponse.getBoolean("use_lfs");
        vcsUrl = hResponse.getString("vcs_url");
        svcRoot = hResponse.getString("svc_root");
        tfvcProject = hResponse.getString("tfvc_project");
        status = ImportStatus.valueOf(hResponse.getString("status"));
        statusText = hResponse.getString("status_text");
        failedStep = hResponse.getString("failed_step");
        errorMessage = hResponse.getString("error_message");
        importPercent = hResponse.getDouble("import_percent", 0);
        commitCount = hResponse.getInt("commit_count", 0);
        pushPercent = hResponse.getDouble("push_percent", 0);
        hasLargeFiles = hResponse.getBoolean("has_large_files");
        largeFilesSize = hResponse.getDouble("large_files_size", 0);
        largeFilesCount = hResponse.getInt("large_files_count", 0);
        projectChooses = new ArrayList<>();
        JSONArray jProjectChooses = hResponse.getJSONArray("project_choices", new JSONArray());
        for (int j = 0; j < jProjectChooses.length(); j++)
            projectChooses.add(new ProjectChoose(jProjectChooses.getJSONObject(j)));
        authorsCount = hResponse.getInt("authors_count", 0);
        url = hResponse.getString("url");
        htmlUrl = hResponse.getString("html_url");
        authorsUrl = hResponse.getString("authors_url");
        repositoryUrl = hResponse.getString("repository_url");
        svnRoot = hResponse.getString("svn_root");
    }

    /**
     * Method to get {@link #vcs} instance <br>
     * No-any params required
     *
     * @return {@link #vcs} instance as {@link String}
     **/
    public VCS getVcs() {
        return vcs;
    }

    /**
     * Method to get {@link #useLfs} instance <br>
     * No-any params required
     *
     * @return {@link #useLfs} instance as boolean
     **/
    public boolean useLfs() {
        return useLfs;
    }

    /**
     * Method to get {@link #vcsUrl} instance <br>
     * No-any params required
     *
     * @return {@link #vcsUrl} instance as {@link String}
     **/
    public String getVcsUrl() {
        return vcsUrl;
    }

    /**
     * Method to get {@link #svcRoot} instance <br>
     * No-any params required
     *
     * @return {@link #svcRoot} instance as {@link String}
     **/
    public String getSvcRoot() {
        return svcRoot;
    }

    /**
     * Method to get {@link #tfvcProject} instance <br>
     * No-any params required
     *
     * @return {@link #tfvcProject} instance as {@link String}
     **/
    public String getTfvcProject() {
        return tfvcProject;
    }

    /**
     * Method to get {@link #status} instance <br>
     * No-any params required
     *
     * @return {@link #status} instance as {@link ImportStatus}
     **/
    public ImportStatus getStatus() {
        return status;
    }

    /**
     * Method to get {@link #statusText} instance <br>
     * No-any params required
     *
     * @return {@link #statusText} instance as {@link String}
     **/
    public String getStatusText() {
        return statusText;
    }

    /**
     * Method to get {@link #failedStep} instance <br>
     * No-any params required
     *
     * @return {@link #failedStep} instance as {@link String}
     **/
    public String getFailedStep() {
        return failedStep;
    }

    /**
     * Method to get {@link #errorMessage} instance <br>
     * No-any params required
     *
     * @return {@link #errorMessage} instance as {@link String}
     **/
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * Method to get {@link #importPercent} instance <br>
     * No-any params required
     *
     * @return {@link #importPercent} instance as double
     **/
    public double getImportPercent() {
        return importPercent;
    }

    /**
     * Method to get {@link #importPercent} instance
     *
     * @param decimals: number of digits to round final value
     * @return {@link #importPercent} instance rounded with decimal digits inserted
     * @throws IllegalArgumentException if decimalDigits is negative
     **/
    public double getImportPercent(int decimals) {
        return roundValue(importPercent, decimals);
    }

    /**
     * Method to get {@link #commitCount} instance <br>
     * No-any params required
     *
     * @return {@link #commitCount} instance as int
     **/
    public int getCommitCount() {
        return commitCount;
    }

    /**
     * Method to get {@link #pushPercent} instance <br>
     * No-any params required
     *
     * @return {@link #pushPercent} instance as double
     **/
    public double getPushPercent() {
        return pushPercent;
    }

    /**
     * Method to get {@link #pushPercent} instance
     *
     * @param decimals: number of digits to round final value
     * @return {@link #pushPercent} instance rounded with decimal digits inserted
     * @throws IllegalArgumentException if decimalDigits is negative
     **/
    public double getPushPercent(int decimals) {
        return roundValue(pushPercent, decimals);
    }

    /**
     * Method to get {@link #hasLargeFiles} instance <br>
     * No-any params required
     *
     * @return {@link #hasLargeFiles} instance as boolean
     **/
    public boolean isHasLargeFiles() {
        return hasLargeFiles;
    }

    /**
     * Method to get {@link #largeFilesSize} instance <br>
     * No-any params required
     *
     * @return {@link #largeFilesSize} instance as double
     **/
    public double getLargeFilesSize() {
        return largeFilesSize;
    }

    /**
     * Method to get {@link #largeFilesSize} instance
     *
     * @param decimals: number of digits to round final value
     * @return {@link #largeFilesSize} instance rounded with decimal digits inserted
     * @throws IllegalArgumentException if decimalDigits is negative
     **/
    public double getLargeFilesSize(int decimals) {
        return roundValue(largeFilesSize, decimals);
    }

    /**
     * Method to get {@link #largeFilesCount} instance <br>
     * No-any params required
     *
     * @return {@link #largeFilesCount} instance as int
     **/
    public int getLargeFilesCount() {
        return largeFilesCount;
    }

    /**
     * Method to get {@link #projectChooses} instance <br>
     * No-any params required
     *
     * @return {@link #projectChooses} instance as {@link ArrayList} of {@link ProjectChoose}
     **/
    public ArrayList<ProjectChoose> getProjectChooses() {
        return projectChooses;
    }

    /**
     * Method to get {@link #authorsCount} instance <br>
     * No-any params required
     *
     * @return {@link #authorsCount} instance as int
     **/
    public int getAuthorsCount() {
        return authorsCount;
    }

    /**
     * Method to get {@link #url} instance <br>
     * No-any params required
     *
     * @return {@link #url} instance as {@link String}
     **/
    public String getUrl() {
        return url;
    }

    /**
     * Method to get {@link #htmlUrl} instance <br>
     * No-any params required
     *
     * @return {@link #htmlUrl} instance as {@link String}
     **/
    public String getHtmlUrl() {
        return htmlUrl;
    }

    /**
     * Method to get {@link #authorsUrl} instance <br>
     * No-any params required
     *
     * @return {@link #authorsUrl} instance as {@link String}
     **/
    public String getAuthorsUrl() {
        return authorsUrl;
    }

    /**
     * Method to get {@link #repositoryUrl} instance <br>
     * No-any params required
     *
     * @return {@link #repositoryUrl} instance as {@link String}
     **/
    public String getRepositoryUrl() {
        return repositoryUrl;
    }

    /**
     * Method to get {@link #svnRoot} instance <br>
     * No-any params required
     *
     * @return {@link #svnRoot} instance as {@link String}
     **/
    public String getSvnRoot() {
        return svnRoot;
    }

    /**
     * The {@code ProjectChoose} class is useful to format a GitHub's project choose
     *
     * @author N7ghtm4r3 - Tecknobit
     * @see InnerClassItem
     **/
    public static class ProjectChoose extends InnerClassItem {

        /**
         * {@code vcs} the project choose
         **/
        private final String vcs;

        /**
         * {@code tfvcProject} tfvc of the project choose
         **/
        private final String tfvcProject;

        /**
         * {@code humanName} human name of the project choose
         **/
        private final String humanName;

        /**
         * Constructor to init a {@link ProjectChoose}
         *
         * @param vcs:         the project choose
         * @param tfvcProject: tfvc of the project choose
         * @param humanName:   human name of the project choose
         **/
        public ProjectChoose(String vcs, String tfvcProject, String humanName) {
            super(null);
            this.vcs = vcs;
            this.tfvcProject = tfvcProject;
            this.humanName = humanName;
        }

        /**
         * Constructor to init a {@link ProjectChoose}
         *
         * @param jProjectChoose: project choose details as {@link JSONObject}
         **/
        public ProjectChoose(JSONObject jProjectChoose) {
            super(jProjectChoose);
            vcs = hItem.getString("vcs");
            tfvcProject = hItem.getString("tfvc_project");
            humanName = hItem.getString("human_name");
        }

        /**
         * Method to get {@link #vcs} instance <br>
         * No-any params required
         *
         * @return {@link #vcs} instance as {@link String}
         **/
        public String getVcs() {
            return vcs;
        }

        /**
         * Method to get {@link #tfvcProject} instance <br>
         * No-any params required
         *
         * @return {@link #tfvcProject} instance as {@link String}
         **/
        public String getTfvcProject() {
            return tfvcProject;
        }

        /**
         * Method to get {@link #humanName} instance <br>
         * No-any params required
         *
         * @return {@link #humanName} instance as {@link String}
         **/
        public String getHumanName() {
            return humanName;
        }

    }

}
