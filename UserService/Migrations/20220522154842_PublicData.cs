using System;
using Microsoft.EntityFrameworkCore.Migrations;

namespace UserService.Migrations
{
    public partial class PublicData : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.AddColumn<string>(
                name: "Authority",
                table: "Documents",
                type: "nvarchar(max)",
                nullable: true);

            migrationBuilder.AddColumn<DateTime>(
                name: "DateOfExpiry",
                table: "Documents",
                type: "datetime2",
                nullable: false,
                defaultValue: new DateTime(1, 1, 1, 0, 0, 0, 0, DateTimeKind.Unspecified));

            migrationBuilder.AddColumn<DateTime>(
                name: "DateOfIssue",
                table: "Documents",
                type: "datetime2",
                nullable: false,
                defaultValue: new DateTime(1, 1, 1, 0, 0, 0, 0, DateTimeKind.Unspecified));

            migrationBuilder.AddColumn<string>(
                name: "ImageName",
                table: "Documents",
                type: "nvarchar(max)",
                nullable: true);

            migrationBuilder.AddColumn<string>(
                name: "ImageURL",
                table: "Documents",
                type: "nvarchar(max)",
                nullable: true);

            migrationBuilder.AddColumn<string>(
                name: "Nationality",
                table: "Documents",
                type: "nvarchar(max)",
                nullable: true);
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropColumn(
                name: "Authority",
                table: "Documents");

            migrationBuilder.DropColumn(
                name: "DateOfExpiry",
                table: "Documents");

            migrationBuilder.DropColumn(
                name: "DateOfIssue",
                table: "Documents");

            migrationBuilder.DropColumn(
                name: "ImageName",
                table: "Documents");

            migrationBuilder.DropColumn(
                name: "ImageURL",
                table: "Documents");

            migrationBuilder.DropColumn(
                name: "Nationality",
                table: "Documents");
        }
    }
}
