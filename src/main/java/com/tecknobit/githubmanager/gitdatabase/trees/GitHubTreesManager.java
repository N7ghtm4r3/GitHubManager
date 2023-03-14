package com.tecknobit.githubmanager.gitdatabase.trees;

import com.tecknobit.apimanager.annotations.RequestPath;
import com.tecknobit.apimanager.annotations.Returner;
import com.tecknobit.apimanager.annotations.WrappedRequest;
import com.tecknobit.apimanager.annotations.Wrapper;
import com.tecknobit.githubmanager.GitHubManager;
import com.tecknobit.githubmanager.gitdatabase.trees.records.Tree;
import com.tecknobit.githubmanager.gitdatabase.trees.records.Tree.TreeValue;
import com.tecknobit.githubmanager.repositories.repositories.records.Repository;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import static com.tecknobit.apimanager.apis.APIRequest.RequestMethod.GET;
import static com.tecknobit.apimanager.apis.APIRequest.RequestMethod.POST;
import static com.tecknobit.githubmanager.GitHubManager.ReturnFormat.LIBRARY_OBJECT;
import static com.tecknobit.githubmanager.gitdatabase.blobs.GitHubBlobsManager.GIT_PATH;

/**
 * The {@code GitHubTreesManager} class is useful to manage all GitHub's trees endpoints
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/git/trees">
 * Git trees</a>
 * @see GitHubManager
 **/
public class GitHubTreesManager extends GitHubManager {

    /**
     * {@code GIT_TREES_PATH} constant for {@code "git/trees"} path
     **/
    public static final String GIT_TREES_PATH = GIT_PATH + "trees";

    /**
     * Constructor to init a {@link GitHubTreesManager}
     *
     * @param accessToken : personal access token for authentication to {@code "GitHub"}
     **/
    public GitHubTreesManager(String accessToken) {
        super(accessToken);
    }

    /**
     * Constructor to init a {@link GitHubTreesManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     **/
    public GitHubTreesManager(String accessToken, String defaultErrorMessage) {
        super(accessToken, defaultErrorMessage);
    }

    /**
     * Constructor to init a {@link GitHubTreesManager}
     *
     * @param accessToken    :    personal access token for authentication to {@code "GitHub"}
     * @param requestTimeout : custom timeout for request
     **/
    public GitHubTreesManager(String accessToken, int requestTimeout) {
        super(accessToken, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubTreesManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     * @param requestTimeout      :      custom timeout for request
     **/
    public GitHubTreesManager(String accessToken, String defaultErrorMessage, int requestTimeout) {
        super(accessToken, defaultErrorMessage, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubTreesManager} <br>
     * No-any params required
     *
     * @throws IllegalArgumentException when a parameterized constructor has not been called before this constructor
     * @apiNote this constructor is useful to instantiate a new {@link GitHubManager}'s manager without re-insert
     * the credentials and is useful in those cases if you need to use different manager at the same time:
     * <pre>
     *     {@code
     *        //You need to insert all credentials requested
     *        GitHubManager firstManager = new GitHubManager("accessToken");
     *        //You don't need to insert all credentials to make manager work
     *        GitHubManager secondManager = new GitHubManager(); //same credentials used
     *     }
     * </pre>
     **/
    public GitHubTreesManager() {
        super();
    }

    /**
     * Method to create a tree <br>
     * The tree creation API accepts nested entries. If you specify both a tree and a nested path modifying that tree,
     * this endpoint will overwrite the contents of the tree with the new path contents, and create a new tree structure.
     * If you use this endpoint to add, delete, or modify the file contents in a tree, you will need to commit the tree and
     * then update a branch to point to the commit. For more information see "Create a commit" and "Update a reference."
     * Returns an error if you try to delete a file that does not exist
     *
     * @param repository: the repository where create the tree
     * @param tree:       array of {@link TreeValue}
     * @return tree as {@link Tree} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/git/trees#create-a-tree">
     * Create a tree</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/git/trees")
    public Tree createTree(Repository repository, ArrayList<TreeValue> tree) throws IOException {
        return createTree(repository.getOwner().getLogin(), repository.getName(), tree, LIBRARY_OBJECT);
    }

    /**
     * Method to create a tree <br>
     * The tree creation API accepts nested entries. If you specify both a tree and a nested path modifying that tree,
     * this endpoint will overwrite the contents of the tree with the new path contents, and create a new tree structure.
     * If you use this endpoint to add, delete, or modify the file contents in a tree, you will need to commit the tree and
     * then update a branch to point to the commit. For more information see "Create a commit" and "Update a reference."
     * Returns an error if you try to delete a file that does not exist
     *
     * @param repository: the repository where create the tree
     * @param tree:       array of {@link TreeValue}
     * @param format      :               return type formatter -> {@link ReturnFormat}
     * @return tree as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/git/trees#create-a-tree">
     * Create a tree</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/git/trees")
    public <T> T createTree(Repository repository, ArrayList<TreeValue> tree, ReturnFormat format) throws IOException {
        return createTree(repository.getOwner().getLogin(), repository.getName(), tree, LIBRARY_OBJECT);
    }

    /**
     * Method to create a tree <br>
     * The tree creation API accepts nested entries. If you specify both a tree and a nested path modifying that tree,
     * this endpoint will overwrite the contents of the tree with the new path contents, and create a new tree structure.
     * If you use this endpoint to add, delete, or modify the file contents in a tree, you will need to commit the tree and
     * then update a branch to point to the commit. For more information see "Create a commit" and "Update a reference."
     * Returns an error if you try to delete a file that does not exist
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @param tree:  array of {@link TreeValue}
     * @return tree as {@link Tree} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/git/trees#create-a-tree">
     * Create a tree</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/git/trees")
    public Tree createTree(String owner, String repo, ArrayList<TreeValue> tree) throws IOException {
        return createTree(owner, repo, tree, LIBRARY_OBJECT);
    }

    /**
     * Method to create a tree <br>
     * The tree creation API accepts nested entries. If you specify both a tree and a nested path modifying that tree,
     * this endpoint will overwrite the contents of the tree with the new path contents, and create a new tree structure.
     * If you use this endpoint to add, delete, or modify the file contents in a tree, you will need to commit the tree and
     * then update a branch to point to the commit. For more information see "Create a commit" and "Update a reference."
     * Returns an error if you try to delete a file that does not exist
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @param tree:  array of {@link TreeValue}
     * @param format :               return type formatter -> {@link ReturnFormat}
     * @return tree as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/git/trees#create-a-tree">
     * Create a tree</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/git/trees")
    public <T> T createTree(String owner, String repo, ArrayList<TreeValue> tree, ReturnFormat format) throws IOException {
        return createTree(owner, repo, tree, null, format);
    }

    /**
     * Method to create a tree <br>
     * The tree creation API accepts nested entries. If you specify both a tree and a nested path modifying that tree,
     * this endpoint will overwrite the contents of the tree with the new path contents, and create a new tree structure.
     * If you use this endpoint to add, delete, or modify the file contents in a tree, you will need to commit the tree and
     * then update a branch to point to the commit. For more information see "Create a commit" and "Update a reference."
     * Returns an error if you try to delete a file that does not exist
     *
     * @param repository: the repository where create the tree
     * @param tree:       array of {@link TreeValue}
     * @param baseTree:   the SHA1 of an existing Git tree object which will be used as the base for the new tree.
     *                    If provided, a new Git tree object will be created from entries in the Git tree object pointed to
     *                    by base_tree and entries defined in the tree parameter. Entries defined in the tree parameter will
     *                    overwrite items from {@code "base_tree"} with the same path. If you're creating new changes on a branch,
     *                    then normally you'd set base_tree to the SHA1 of the Git tree object of the current latest commit
     *                    on the branch you're working on. If not provided, GitHub will create a new Git tree object from
     *                    only the entries defined in the tree parameter. If you create a new commit pointing to such a tree,
     *                    then all files which were a part of the parent commit's tree and were not defined in the tree
     *                    parameter will be listed as deleted by the new commit
     * @return tree as {@link Tree} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/git/trees#create-a-tree">
     * Create a tree</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/git/trees")
    public Tree createTree(Repository repository, ArrayList<TreeValue> tree, String baseTree) throws IOException {
        return createTree(repository.getOwner().getLogin(), repository.getName(), tree, baseTree, LIBRARY_OBJECT);
    }

    /**
     * Method to create a tree <br>
     * The tree creation API accepts nested entries. If you specify both a tree and a nested path modifying that tree,
     * this endpoint will overwrite the contents of the tree with the new path contents, and create a new tree structure.
     * If you use this endpoint to add, delete, or modify the file contents in a tree, you will need to commit the tree and
     * then update a branch to point to the commit. For more information see "Create a commit" and "Update a reference."
     * Returns an error if you try to delete a file that does not exist
     *
     * @param repository: the repository where create the tree
     * @param tree:       array of {@link TreeValue}
     * @param baseTree:   the SHA1 of an existing Git tree object which will be used as the base for the new tree.
     *                    If provided, a new Git tree object will be created from entries in the Git tree object pointed to
     *                    by base_tree and entries defined in the tree parameter. Entries defined in the tree parameter will
     *                    overwrite items from {@code "base_tree"} with the same path. If you're creating new changes on a branch,
     *                    then normally you'd set base_tree to the SHA1 of the Git tree object of the current latest commit
     *                    on the branch you're working on. If not provided, GitHub will create a new Git tree object from
     *                    only the entries defined in the tree parameter. If you create a new commit pointing to such a tree,
     *                    then all files which were a part of the parent commit's tree and were not defined in the tree
     *                    parameter will be listed as deleted by the new commit
     * @param format      :               return type formatter -> {@link ReturnFormat}
     * @return tree as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/git/trees#create-a-tree">
     * Create a tree</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/git/trees")
    public <T> T createTree(Repository repository, ArrayList<TreeValue> tree, String baseTree,
                            ReturnFormat format) throws IOException {
        return createTree(repository.getOwner().getLogin(), repository.getName(), tree, baseTree, LIBRARY_OBJECT);
    }

    /**
     * Method to create a tree <br>
     * The tree creation API accepts nested entries. If you specify both a tree and a nested path modifying that tree,
     * this endpoint will overwrite the contents of the tree with the new path contents, and create a new tree structure.
     * If you use this endpoint to add, delete, or modify the file contents in a tree, you will need to commit the tree and
     * then update a branch to point to the commit. For more information see "Create a commit" and "Update a reference."
     * Returns an error if you try to delete a file that does not exist
     *
     * @param owner:    the account owner of the repository. The name is not case-sensitive
     * @param repo:     the name of the repository. The name is not case-sensitive
     * @param tree:     array of {@link TreeValue}
     * @param baseTree: the SHA1 of an existing Git tree object which will be used as the base for the new tree.
     *                  If provided, a new Git tree object will be created from entries in the Git tree object pointed to
     *                  by base_tree and entries defined in the tree parameter. Entries defined in the tree parameter will
     *                  overwrite items from {@code "base_tree"} with the same path. If you're creating new changes on a branch,
     *                  then normally you'd set base_tree to the SHA1 of the Git tree object of the current latest commit
     *                  on the branch you're working on. If not provided, GitHub will create a new Git tree object from
     *                  only the entries defined in the tree parameter. If you create a new commit pointing to such a tree,
     *                  then all files which were a part of the parent commit's tree and were not defined in the tree
     *                  parameter will be listed as deleted by the new commit
     * @return tree as {@link Tree} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/git/trees#create-a-tree">
     * Create a tree</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/git/trees")
    public Tree createTree(String owner, String repo, ArrayList<TreeValue> tree, String baseTree) throws IOException {
        return createTree(owner, repo, tree, baseTree, LIBRARY_OBJECT);
    }

    /**
     * Method to create a tree <br>
     * The tree creation API accepts nested entries. If you specify both a tree and a nested path modifying that tree,
     * this endpoint will overwrite the contents of the tree with the new path contents, and create a new tree structure.
     * If you use this endpoint to add, delete, or modify the file contents in a tree, you will need to commit the tree and
     * then update a branch to point to the commit. For more information see "Create a commit" and "Update a reference."
     * Returns an error if you try to delete a file that does not exist
     *
     * @param owner:    the account owner of the repository. The name is not case-sensitive
     * @param repo:     the name of the repository. The name is not case-sensitive
     * @param tree:     array of {@link TreeValue}
     * @param baseTree: the SHA1 of an existing Git tree object which will be used as the base for the new tree.
     *                  If provided, a new Git tree object will be created from entries in the Git tree object pointed to
     *                  by base_tree and entries defined in the tree parameter. Entries defined in the tree parameter will
     *                  overwrite items from {@code "base_tree"} with the same path. If you're creating new changes on a branch,
     *                  then normally you'd set base_tree to the SHA1 of the Git tree object of the current latest commit
     *                  on the branch you're working on. If not provided, GitHub will create a new Git tree object from
     *                  only the entries defined in the tree parameter. If you create a new commit pointing to such a tree,
     *                  then all files which were a part of the parent commit's tree and were not defined in the tree
     *                  parameter will be listed as deleted by the new commit
     * @param format    :               return type formatter -> {@link ReturnFormat}
     * @return tree as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/git/trees#create-a-tree">
     * Create a tree</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/git/trees")
    public <T> T createTree(String owner, String repo, ArrayList<TreeValue> tree, String baseTree,
                            ReturnFormat format) throws IOException {
        Params payload = new Params();
        payload.addParam("tree", tree);
        if (baseTree != null)
            payload.addParam("base_tree", baseTree);
        return returnTree(sendPostRequest(REPOS_PATH + owner + "/" + repo + "/" + GIT_TREES_PATH, payload),
                format);
    }

    /**
     * Method to return a single tree using the SHA1 value for that tree. <br>
     * If truncated is {@code "true"} in the response then the number of items in the tree array exceeded our maximum limit. <br>
     * If you need to fetch more items, use the non-recursive method of fetching trees, and fetch one subtree at a time
     *
     * @param repository: the repository from fetch the tree
     * @param treeSha:    tree sha value
     * @return tree as {@link Tree} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/git/trees#get-a-tree">
     * Get a tree</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/git/trees/{tree_sha}")
    public Tree getTree(Repository repository, String treeSha) throws IOException {
        return getTree(repository.getOwner().getLogin(), repository.getName(), treeSha, LIBRARY_OBJECT);
    }

    /**
     * Method to return a single tree using the SHA1 value for that tree. <br>
     * If truncated is {@code "true"} in the response then the number of items in the tree array exceeded our maximum limit. <br>
     * If you need to fetch more items, use the non-recursive method of fetching trees, and fetch one subtree at a time
     *
     * @param repository: the repository from fetch the tree
     * @param treeSha:    tree sha value
     * @param format      :               return type formatter -> {@link ReturnFormat}
     * @return tree as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/git/trees#get-a-tree">
     * Get a tree</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/git/trees/{tree_sha}")
    public <T> T getTree(Repository repository, String treeSha, ReturnFormat format) throws IOException {
        return getTree(repository.getOwner().getLogin(), repository.getName(), treeSha, format);
    }

    /**
     * Method to return a single tree using the SHA1 value for that tree. <br>
     * If truncated is {@code "true"} in the response then the number of items in the tree array exceeded our maximum limit. <br>
     * If you need to fetch more items, use the non-recursive method of fetching trees, and fetch one subtree at a time
     *
     * @param owner:   the account owner of the repository. The name is not case-sensitive
     * @param repo:    the name of the repository. The name is not case-sensitive
     * @param treeSha: tree sha value
     * @return tree as {@link Tree} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/git/trees#get-a-tree">
     * Get a tree</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/git/trees/{tree_sha}")
    public Tree getTree(String owner, String repo, String treeSha) throws IOException {
        return getTree(owner, repo, treeSha, LIBRARY_OBJECT);
    }

    /**
     * Method to return a single tree using the SHA1 value for that tree. <br>
     * If truncated is {@code "true"} in the response then the number of items in the tree array exceeded our maximum limit. <br>
     * If you need to fetch more items, use the non-recursive method of fetching trees, and fetch one subtree at a time
     *
     * @param owner:   the account owner of the repository. The name is not case-sensitive
     * @param repo:    the name of the repository. The name is not case-sensitive
     * @param treeSha: tree sha value
     * @param format   :               return type formatter -> {@link ReturnFormat}
     * @return tree as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/git/trees#get-a-tree">
     * Get a tree</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/git/trees/{tree_sha}")
    public <T> T getTree(String owner, String repo, String treeSha, ReturnFormat format) throws IOException {
        return returnTree(sendGetRequest(REPOS_PATH + owner + "/" + repo + "/" + GIT_TREES_PATH + treeSha),
                format);
    }

    /**
     * Method to return a single tree using the SHA1 value for that tree. <br>
     * If truncated is {@code "true"} in the response then the number of items in the tree array exceeded our maximum limit. <br>
     * If you need to fetch more items, use the non-recursive method of fetching trees, and fetch one subtree at a time
     *
     * @param repository: the repository from fetch the tree
     * @param treeSha:    tree sha value
     * @param recursive:  setting this parameter to any value returns the objects or subtrees referenced by the tree
     *                    specified in {@code ":tree_sha"}. For example, setting recursive to any of the following will
     *                    enable returning objects or subtrees: {@code 0}, {@code 1}, {@code "true"}, and {@code "false"}.
     *                    Omit this parameter to prevent recursively returning objects or subtrees
     * @return tree as {@link Tree} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/git/trees#get-a-tree">
     * Get a tree</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/git/trees/{tree_sha}")
    public Tree getTree(Repository repository, String treeSha, String recursive) throws IOException {
        return getTree(repository.getOwner().getLogin(), repository.getName(), treeSha, recursive, LIBRARY_OBJECT);
    }

    /**
     * Method to return a single tree using the SHA1 value for that tree. <br>
     * If truncated is {@code "true"} in the response then the number of items in the tree array exceeded our maximum limit. <br>
     * If you need to fetch more items, use the non-recursive method of fetching trees, and fetch one subtree at a time
     *
     * @param repository: the repository from fetch the tree
     * @param treeSha:    tree sha value
     * @param recursive:  setting this parameter to any value returns the objects or subtrees referenced by the tree
     *                    specified in {@code ":tree_sha"}. For example, setting recursive to any of the following will
     *                    enable returning objects or subtrees: {@code 0}, {@code 1}, {@code "true"}, and {@code "false"}.
     *                    Omit this parameter to prevent recursively returning objects or subtrees
     * @param format      :               return type formatter -> {@link ReturnFormat}
     * @return tree as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/git/trees#get-a-tree">
     * Get a tree</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/git/trees/{tree_sha}")
    public <T> T getTree(Repository repository, String treeSha, String recursive, ReturnFormat format) throws IOException {
        return getTree(repository.getOwner().getLogin(), repository.getName(), treeSha, recursive, format);
    }

    /**
     * Method to return a single tree using the SHA1 value for that tree. <br>
     * If truncated is {@code "true"} in the response then the number of items in the tree array exceeded our maximum limit. <br>
     * If you need to fetch more items, use the non-recursive method of fetching trees, and fetch one subtree at a time
     *
     * @param owner:     the account owner of the repository. The name is not case-sensitive
     * @param repo:      the name of the repository. The name is not case-sensitive
     * @param treeSha:   tree sha value
     * @param recursive: setting this parameter to any value returns the objects or subtrees referenced by the tree
     *                   specified in {@code ":tree_sha"}. For example, setting recursive to any of the following will
     *                   enable returning objects or subtrees: {@code 0}, {@code 1}, {@code "true"}, and {@code "false"}.
     *                   Omit this parameter to prevent recursively returning objects or subtrees
     * @return tree as {@link Tree} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/git/trees#get-a-tree">
     * Get a tree</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/git/trees/{tree_sha}")
    public Tree getTree(String owner, String repo, String treeSha, String recursive) throws IOException {
        return getTree(owner, repo, treeSha, recursive, LIBRARY_OBJECT);
    }

    /**
     * Method to return a single tree using the SHA1 value for that tree. <br>
     * If truncated is {@code "true"} in the response then the number of items in the tree array exceeded our maximum limit. <br>
     * If you need to fetch more items, use the non-recursive method of fetching trees, and fetch one subtree at a time
     *
     * @param owner:     the account owner of the repository. The name is not case-sensitive
     * @param repo:      the name of the repository. The name is not case-sensitive
     * @param treeSha:   tree sha value
     * @param recursive: setting this parameter to any value returns the objects or subtrees referenced by the tree
     *                   specified in {@code ":tree_sha"}. For example, setting recursive to any of the following will
     *                   enable returning objects or subtrees: {@code 0}, {@code 1}, {@code "true"}, and {@code "false"}.
     *                   Omit this parameter to prevent recursively returning objects or subtrees
     * @param format     :               return type formatter -> {@link ReturnFormat}
     * @return tree as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/git/trees#get-a-tree">
     * Get a tree</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/git/trees/{tree_sha}")
    public <T> T getTree(String owner, String repo, String treeSha, String recursive,
                         ReturnFormat format) throws IOException {
        return returnTree(sendGetRequest(REPOS_PATH + owner + "/" + repo + "/" + GIT_TREES_PATH + "/" + treeSha
                + "?recursive=" + recursive), format);
    }

    /**
     * Method to create a tree
     *
     * @param treeResponse : obtained from GitHub's response
     * @param format       :               return type formatter -> {@link ReturnFormat}
     * @return tree as {@code "format"} defines
     **/
    @Returner
    private <T> T returnTree(String treeResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONObject(treeResponse);
            case LIBRARY_OBJECT:
                return (T) new Tree(new JSONObject(treeResponse));
            default:
                return (T) treeResponse;
        }
    }

}
