package org.exoplatform.social.notification;

import org.exoplatform.commons.utils.CommonsUtils;
import org.exoplatform.forum.service.ForumService;
import org.exoplatform.services.log.ExoLogger;
import org.exoplatform.services.log.Log;
import org.exoplatform.social.core.activity.model.ExoSocialActivity;
import org.exoplatform.social.core.identity.model.Profile;
import org.exoplatform.social.core.service.LinkProvider;
import org.exoplatform.social.core.space.model.Space;
import org.exoplatform.social.core.activity.model.ActivityPluginType;

import java.util.Map;

public class LinkProviderUtils {
  
public static final String RESOURCE_URL = "social/notifications";
  
  public static final String INVITE_TO_CONNECT = RESOURCE_URL + "/inviteToConnect";
  
  public static final String CONFIRM_INVITATION_TO_CONNECT = RESOURCE_URL + "/confirmInvitationToConnect";
  
  public static final String IGNORE_INVITATION_TO_CONNECT = RESOURCE_URL + "/ignoreInvitationToConnect";
  
  public static final String ACCEPT_INVITATION_JOIN_SPACE = RESOURCE_URL + "/acceptInvitationToJoinSpace";
  
  public static final String IGNORE_INVITATION_JOIN_SPACE = RESOURCE_URL + "/ignoreInvitationToJoinSpace";
  
  public static final String VALIDATE_REQUEST_JOIN_SPACE = RESOURCE_URL + "/validateRequestToJoinSpace";
  
  public static final String REFUSE_SPACE_REQUEST_ACTION = RESOURCE_URL + "/refuseRequestToJoinSpace";
  
  public static final String REPLY_ACTIVITY = RESOURCE_URL + "/replyActivity";
  
  public static final String VIEW_FULL_DISCUSSION = RESOURCE_URL + "/viewFullDiscussion";
  
  public static final String REDIRECT_URL = RESOURCE_URL + "/redirectUrl";
  
  public static final String PRIVATE_PATH = "/private";

  private static final Log LOG = ExoLogger.getLogger(LinkProviderUtils.class);

  /**
   * Gets the url to the user's profile page of the receiver
   * 
   * @param senderId remoteId of the sender
   * @param receiverId remoteId of the receiver
   * @return
   */
  public static String getInviteToConnectUrl(String receiverId, String senderId) {
    return getRestUrl(INVITE_TO_CONNECT, receiverId, senderId);
  }
  
  /**
   * Gets the url to the user's profile page of the sender
   * @param senderId remoteId of the sender
   * @param receiverId remoteId of the receiver
   * @return
   */
  public static String getConfirmInvitationToConnectUrl(String senderId, String receiverId) {
    return getPrivateRestUrl(CONFIRM_INVITATION_TO_CONNECT, senderId, receiverId);
  }
  
  /**
   * Gets the url to the connection's tab of the current user
   * 
   * @param senderId remoteId of the sender
   * @param receiverId remoteId of the receiver
   * @return
   */
  public static String getIgnoreInvitationToConnectUrl(String senderId, String receiverId) {
    return getPrivateRestUrl(IGNORE_INVITATION_TO_CONNECT, senderId, receiverId);
  }
  
  /**
   * Gets the url to the space's home page
   * 
   * @param spaceId
   * @param userId
   * @return
   */
  public static String getAcceptInvitationToJoinSpaceUrl(String spaceId, String userId) {
    return getPrivateRestUrl(ACCEPT_INVITATION_JOIN_SPACE, spaceId, userId);
  }
  
  /**
   * Gets the url to the space's home page
   * 
   * @param spaceId
   * @param userId remoteId of the user
   * @return
   */
  public static String getIgnoreInvitationToJoinSpaceUrl(String spaceId, String userId) {
    return getPrivateRestUrl(IGNORE_INVITATION_JOIN_SPACE, spaceId, userId);
  }
  
  /**
   * Gets the url to the space's members
   * @param spaceId
   * @param userId remoteId of the user
   * @return
   */
  public static String getValidateRequestToJoinSpaceUrl(String spaceId, String userId) {
    return getRestUrl(VALIDATE_REQUEST_JOIN_SPACE, spaceId, userId);
  }
  
  /**
   * Gets the url to the space's members
   * @param spaceId
   * @param userId remoteId of the user
   * @return
   */
  public static String getRefuseRequestToJoinSpaceUrl(String spaceId, String userId) {
    return getRestUrl(REFUSE_SPACE_REQUEST_ACTION, spaceId, userId);
  }
  
  /**
   * Gets the associated page of type
   * @param type type of the page : user or space or activity
   * @param objectId can be a space's id or user's id or activity's id
   * @return
   */
  public static String getRedirectUrl(String type, String objectId) {
    return getRestUrl(REDIRECT_URL, type, objectId);
  }
  
  public static String getWebNotificationRestUrl(String type, String objectId1, String objectId2) {
    String restContext = CommonsUtils.getRestContextName();
    return new StringBuffer("/").append(restContext).append("/").append(type)
                                .append("/").append(objectId1).append("/").append(objectId2).toString();
  }
  /**
   * Gets the rest service URI for the specified context
   * 
   * @param type
   * @param objectId1
   * @param objectId2
   * @param jsonFile the json file name
   * @return
   */
  public static String getWebNotificationRestUrl(String type, String objectId1, String objectId2, String notificationId, String jsonFile) {
    String restContext = CommonsUtils.getRestContextName();
    return new StringBuffer("/").append(restContext)
                                .append("/")
                                .append(type)
                                .append("/")
                                .append(objectId1)
                                .append("/")
                                .append(objectId2)
                                .append("/")
                                .append(notificationId)
                                .append("/")
                                .append(jsonFile)
                                .toString();
  }

  /**
   * Gets full rest url
   * 
   * @param type
   * @param objectId1
   * @param objectId2
   * @return
   */
  public static String getRestUrl(String type, String objectId1, String objectId2) {
    String baseUrl = getBaseRestUrl();
    return new StringBuffer(baseUrl).append("/").append(type).append("/").append(objectId1)
                                    .append("/").append(objectId2).toString();
  }
  
  /** 
   * Get base url of rest service
   * 
   * @return base rest url like : http://localhost:8080/rest
   */
  public static String getBaseRestUrl() {
    return new StringBuffer(CommonsUtils.getCurrentDomain()).append("/").append(CommonsUtils.getRestContextName()).toString();
  }
  
  /**
   * Gets private absolute rest url
   * 
   * @param type
   * @param objectId1
   * @param objectId2
   * @return
   */
  public static String getPrivateRestUrl(String type, String objectId1, String objectId2) {
    String baseUrl = getBasePrivateRestUrl();
    return new StringBuffer(baseUrl).append("/").append(type).append("/").append(objectId1)
                                    .append("/").append(objectId2).toString();
  }
  
  /** 
   * Get private absolute base url of rest service
   * 
   * @return base rest url like : http://localhost:8080/rest/private
   */
  public static String getBasePrivateRestUrl() {
    return new StringBuffer(CommonsUtils.getCurrentDomain()).append("/").append(CommonsUtils.getRestContextName()).append(PRIVATE_PATH).toString();
  }
  
  /**
   * Gets the user's avatar url. In case this url is null, we take the default url
   * 
   * @param profile user profile
   * @return
   */
  public static String getUserAvatarUrl(Profile profile) {
    return CommonsUtils.getCurrentDomain() + ((profile != null && profile.getAvatarUrl() != null) ? profile.getAvatarUrl() : LinkProvider.PROFILE_DEFAULT_AVATAR_URL);
  }
  
  /**
   * Gets the space's avatar url. In case this url is null, we take the default url
   * 
   * @param space
   * @return
   */
  public static String getSpaceAvatarUrl(Space space) {
    return CommonsUtils.getCurrentDomain() + ((space != null && space.getAvatarUrl() != null) ? space.getAvatarUrl() : LinkProvider.SPACE_DEFAULT_AVATAR_URL);
  }

  /**
   * Get the open link for each type of notification
   * @param activity The activity of the notification
   * @param isComment Is the activity a comment
   * @return The link to open the related resource (file, event, wiki page, ...)
   */
  public static String getOpenLink(ExoSocialActivity activity, boolean isComment) {
    String activityType = activity.getType();
    if (activityType != null) {
      try {
        Map<String, String> templateParams = activity.getTemplateParams();
        if (activityType.equals(ActivityPluginType.WIKI.getName())) {
          return CommonsUtils.getCurrentDomain() + templateParams.get("page_url");
        } else if (activityType.equals(ActivityPluginType.FORUM.getName())) {
          if (isComment) {
            if (!activity.getTitleId().equals("forum.remove-poll")) {
              return templateParams.get("PostLink");
            }
          } else {
            return CommonsUtils.getCurrentDomain() + templateParams.get("TopicLink");
          }
        } else if (activityType.equals(ActivityPluginType.CALENDAR.getName())) {
          return CommonsUtils.getCurrentDomain() + templateParams.get("EventLink");
        } else if (activityType.contains(ActivityPluginType.ANSWER.getName())) {
          if (isComment) {
            return CommonsUtils.getCurrentDomain() + Utils.getActivityManager().getParentActivity(activity).getTemplateParams().get("Link");
          } else {
            return templateParams.get("Link");
          }
        } else if (activityType.equals(ActivityPluginType.POLL.getName())) {
          try {
            return CommonsUtils.getCurrentDomain() + CommonsUtils.getService(ForumService.class)
                    .getTopicByPath(templateParams.get("PollLink"), false).getLink();
          } catch (Exception e) {
            LOG.error(e.getMessage(), e);
          }
        } else if (activityType.equals(ActivityPluginType.FILE.getName()) || activityType.equals(ActivityPluginType.CONTENT.getName())) {
          return CommonsUtils.getCurrentDomain() + templateParams.get("contenLink");
        }
      } catch (Exception e) {
        LOG.error("Cannot get open link for activity " + activity.getId() + " : " + e.getMessage(), e);
        return null;
      }
    }

    return null;
  }
}
