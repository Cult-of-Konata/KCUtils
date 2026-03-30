import yaml
import os

def update_readme():
    with open('src/main/resources/plugin.yml', 'r') as f:
        plugin_yml = yaml.safe_load(f)

    commands = plugin_yml.get('commands', {})
    
    header = "| Command | Description | Usage | Permission | Aliases |\n"
    separator = "|---------|-------------|-------|------------|---------|\n"
    rows = []
    
    for cmd, info in commands.items():
        desc = info.get('description', '-')
        usage = info.get('usage', f"/{cmd}")
        perm = info.get('permission', '-')
        aliases = ", ".join(info.get('aliases', [])) if info.get('aliases') else "-"
        rows.append(f"| `/{cmd}` | {desc} | `{usage}` | `{perm}` | {aliases} |\n")
    
    new_table = header + separator + "".join(rows)
    
    with open('README.md', 'r') as f:
        readme_content = f.read()
    
    # Simple replacement between ## Commands and the next ## section or EOF
    start_marker = "## Commands\n\n"
    end_marker = "\n## "
    
    start_index = readme_content.find(start_marker)
    if start_index == -1:
        # If ## Commands not found, append to end
        readme_content += "\n## Commands\n\n" + new_table
    else:
        content_after_start = readme_content[start_index + len(start_marker):]
        end_index = content_after_start.find(end_marker)
        
        if end_index == -1:
            # Replace until end of file
            readme_content = readme_content[:start_index + len(start_marker)] + new_table
        else:
            readme_content = readme_content[:start_index + len(start_marker)] + new_table + content_after_start[end_index:]
            
    with open('README.md', 'w') as f:
        f.write(readme_content)

if __name__ == "__main__":
    update_readme()
