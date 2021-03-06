package com.brightsight.joker.data.network

import com.brightsight.joker.core.Const
import com.brightsight.joker.core.model.BranchInfo
import com.brightsight.joker.core.model.RepoJson
import com.brightsight.joker.core.model.UpdateInfo
import okhttp3.ResponseBody
import retrofit2.http.*

private const val REVISION = "revision"
private const val BRANCH = "branch"
private const val REPO = "repo"
private const val FILE = "file"

const val JOKER_FILES = "topjohnwu/magisk-files"
const val JOKER_MAIN = "topjohnwu/Magisk"

interface GithubPageServices {

    @GET("{$FILE}")
    suspend fun fetchUpdateJSON(@Path(FILE) file: String): UpdateInfo
}

interface JSDelivrServices {

    @GET("$JOKER_FILES@{$REVISION}/snet")
    @Streaming
    suspend fun fetchSafetynet(@Path(REVISION) revision: String = Const.SNET_REVISION): ResponseBody

    @GET("$JOKER_FILES@{$REVISION}/bootctl")
    @Streaming
    suspend fun fetchBootctl(@Path(REVISION) revision: String = Const.BOOTCTL_REVISION): ResponseBody

    @GET("$JOKER_MAIN@{$REVISION}/scripts/module_installer.sh")
    @Streaming
    suspend fun fetchInstaller(@Path(REVISION) revision: String): ResponseBody
}

interface RawServices {

    @GET
    suspend fun fetchCustomUpdate(@Url url: String): UpdateInfo

    @GET
    suspend fun fetchRepoInfo(@Url url: String): RepoJson

    @GET
    @Streaming
    suspend fun fetchFile(@Url url: String): ResponseBody

    @GET
    suspend fun fetchString(@Url url: String): String

}

interface GithubApiServices {

    @GET("repos/{$REPO}/branches/{$BRANCH}")
    @Headers("Accept: application/vnd.github.v3+json")
    suspend fun fetchBranch(
        @Path(REPO, encoded = true) repo: String,
        @Path(BRANCH) branch: String
    ): BranchInfo
}

