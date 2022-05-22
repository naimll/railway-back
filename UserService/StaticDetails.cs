using System;
using System.Collections.Generic;
using Duende.IdentityServer;
using Duende.IdentityServer.Models;

namespace UserService
{
    public static class StaticDetails
    {
        public const string Admin = "Admin";
        public const string Passenger = "Passenger";

        //Identity Resource = data of the user
        public static IEnumerable<IdentityResource> IdentityResources =>
            new List<IdentityResource>
            {
                new IdentityResources.OpenId(),
                new IdentityResources.Email(),
                new IdentityResources.Profile()
            };

        public static IEnumerable<ApiScope> ApiScopes =>
            new List<ApiScope>
            {
                new ApiScope("admin", "Railway Server"),
                new ApiScope(name: "read", "Read the data."),
                new ApiScope(name: "write", "Write the data."),
                new ApiScope(name: "delete", "Delete the data."),
                new ApiScope(name: "edit", "Edit the data.")
            };

        public static IEnumerable<Client> Clients =>
            new List<Client>
            {
                new Client
                {
                    ClientId = "client",
                    ClientSecrets = { new Secret("secret".Sha256())},
                    AllowedGrantTypes = GrantTypes.ClientCredentials,
                    AllowedScopes = {"read", "write", "profile"}
                },
                new Client
                {
                    ClientId = "railway",
                    ClientSecrets = { new Secret("secret".Sha256())},
                    AllowedGrantTypes = GrantTypes.Code,
                    RedirectUris = { "https://localhost:8080/signin-oidc" },
                    PostLogoutRedirectUris = { "https://localhost:8080/signout-callback-oidc" },
                    AllowedScopes = new List<string>
                    {
                        IdentityServerConstants.StandardScopes.OpenId,
                        IdentityServerConstants.StandardScopes.Email,
                        IdentityServerConstants.StandardScopes.Profile,
                        "admin"
                    }
                },

            };
    }
}
