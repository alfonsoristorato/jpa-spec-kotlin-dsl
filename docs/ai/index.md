# AI Agent Skills

jpa-spec-kotlin-dsl ships an [Agent Skill](https://docs.claude.com/en/docs/claude-code/skills) that teaches AI
coding assistants like Claude Code how to write, migrate, and debug Spring Data JPA queries with this DSL. The
skill covers choosing a DSL layer, all core operations, nested properties, joins and fetch joins, combining
specifications, the `path` accessor, the Hibernate module, and common troubleshooting steps - the same knowledge
as this documentation site, packaged for an agent to use directly while it edits your code.

The skill source lives in the [`skills/`](https://github.com/alfonsoristorato/jpa-spec-kotlin-dsl/tree/main/skills)
directory of the repository.

## Installing with Claude Code

The skill is distributed as a [plugin](https://docs.claude.com/en/docs/claude-code/plugins) through a
marketplace hosted in this repository. Add the marketplace once, then install the plugin:

```
/plugin marketplace add alfonsoristorato/jpa-spec-kotlin-dsl
/plugin install jpa-spec-kotlin-dsl-skills
```

Claude Code will pick up the skill automatically whenever you work on JPA specifications in Kotlin - no further
configuration needed.

## Installing manually

If you use a different tool, or don't want to use the plugin marketplace, copy the skill directory into any
location your tool loads skills from, for example:

```bash
git clone --depth 1 https://github.com/alfonsoristorato/jpa-spec-kotlin-dsl.git /tmp/jpa-spec-kotlin-dsl-skill-src
cp -r /tmp/jpa-spec-kotlin-dsl-skill-src/skills/jpa-spec-kotlin-dsl .claude/skills/jpa-spec-kotlin-dsl
```

## Keeping up to date

The skill is versioned alongside the library. Re-running `/plugin marketplace update jpa-spec-kotlin-dsl` (or
re-copying the directory for manual installs) picks up the latest content.
